package com.fxmms.www.schedulejob;

import com.fxmms.common.ro.ControllerResult;
import com.fxmms.www.dao.MacDao;
import com.fxmms.www.dao.TaskDao;
import com.fxmms.www.domain.Mac;
import com.fxmms.www.domain.Task;
import com.fxmms.www.rowmapper.MacRowMapper;
import com.fxmms.www.rowmapper.TaskRowMapper;
import com.fxmms.www.thunderinterfaceutil.VisitThunderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * Created by mark on 16/11/10.
 *
 * @usage 定时查询最近1-2小时内录入的mac地址情况，并定时更新数据库
 * @Before-Thinking 1.设置定时查询时间间为每个小时的整点 即 ＊时0秒。
 * 2.每次调用迅雷接口去查询mac地址录入状态之前，先将此定时任务发生时间转化为
 * long型整数，然后跟数据库中task表中数据做比对，相差在一个定值之内（1-2小时的区间）的taskid查询出来，
 * 3.根据taskId去查询符合的记录，得出记录中的downloadid集合，然后根据downloadid进行迅雷录入状态接口请求，并做更新。
 * @After-Thinking 关于定时任务的另一个思路:
 * 问题：原有思路的问题，只能保证每批次任务至少被查询一次，并不能做到完全覆盖。
 * 更改方案：
 * 1.task 中设置一个flag。
 * 2.flag=1,表示录入成功，flag = 0表示没有录入成功
 * 3.flag = 0的情况包含三种：1.初始化状态 2.正在录入状态 3.录入失败状态
 * 4.每次定时任务查询，先查询flag 为 0 的TaskId,查询出TaskId的集合之后，再去查询隶属于某TaskId相对应的非成功
 * 即 status!=2 的downloadId地址，并根据所查询的downloadId访问MAC地址录入状态接口，
 * 如果请求状态接口之后某个downloadId 对应的deviceid ＝ null 则设置对应的task flag 为0，
 * 并设置此downloadId对应的MAC状态为 3 失败。
 */
@Service
public class CheckMacStatusScheduleJob {
    @Autowired
    MacDao macDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @usage 每一个小时0分00秒执行处理
     * 代码可以重构
     */
    @Scheduled(cron = "0 5 * * * ?")
    @Transactional
    public ControllerResult checkMacLoadStatusSchedule() throws ParseException {
        System.out.println("定时任务开始——————－－－－－");
        if (jdbcTemplate == null) {
            System.out.println("jdbcTemplate is null");
        }
        //大坑！注意表名与domian层中实体类的@table(name="xx")的xx相同
        String sql = "select * from mms_task where flag = 0";
        //TaskRowMapper 作为结果集映射类
        List<Task> taskList = jdbcTemplate.query(sql, new TaskRowMapper());
        if (!taskList.isEmpty()) {
            //2.判断taskList是否为empty,若有执行更新，没有不执行任何操作直接exit（说明1-2小时之内并没有录入）
            // 如果有则选取taskList中每个元素的tid,去查询mms_mac表，查询相关的downloadid并封装成List<String> ，并循环请求查询录入
            // 状态接口，并做更新 至少覆盖一次
            int taskArraySize = taskList.size();
            Integer[] taskIdArray = new Integer[taskArraySize];
            String getDownLoadIdsStr = "select * from mms_mac where tid in(";
            int taskIdCount = 0;
            //遍历taskList，拼接查询字符串
            for (Task task : taskList) {
                taskIdArray[taskIdCount] = task.getId();
                if (taskIdCount < taskList.size() - 1) {
                    getDownLoadIdsStr += "?,";
                } else {
                    getDownLoadIdsStr += "?)";
                }
                taskIdCount++;
            }
            getDownLoadIdsStr += " AND status != 2";
            //使用jdbcTemplate获取符合条件的downLoadId集合
            List<Mac> macList = jdbcTemplate.query(getDownLoadIdsStr, taskIdArray, new MacRowMapper());
            //传递downLoadId访问录入迅雷mac地址录入状态接口，接口成功返回code = 0
            //则获取downLoadId并存储，并更改mac录入状态为2，表示录入成功
            //接口返回为非0,则更改mac录入状态为3 表示录入状态为失败
            for (Mac mac : macList) {
                String downLoadIdStr = mac.getDownLoadId();
                String devicedId = VisitThunderInterface.testDownLoadIdIncomeStatus(downLoadIdStr);
                Mac dbMac = macDao.getByUniqueKey("downLoadId", downLoadIdStr);
                Task task = null;
                //boolean flagIsSuccess = true;
                if (devicedId == null) {
                    //表示录入失败
                    dbMac.setStatus(3);
                    task = taskDao.getByUniqueKey("id", dbMac.getTask().getId());
                    task.setFlag(0);
                    taskDao.update(task);
                    //flagIsSuccess = false;
                } else {
                    dbMac.setStatus(2);
                }
                dbMac.setDeviceId(devicedId);
                macDao.update(dbMac);
            }

            //更新批次任务task 的flag 状态
            for (int taskId : taskIdArray) {
                String querySql = "SELECT count(*) FROM mms_mac WHERE tid = "+taskId+" AND deviceId IS null";
                int count = jdbcTemplate.queryForObject(
                        querySql,Integer.class);
                Task task = taskDao.getByUniqueKey("id",taskId);
                if (count > 0) {
                    task.setFlag(0);
                }else {
                    task.setFlag(1);
                }
                System.out.println("-------执行了");
                taskDao.update(task);
            }
            return ControllerResult.valueOf(ControllerResult.SUCCESS, "成功", macList);
        } else {
            return ControllerResult.valueOf(ControllerResult.SUCCESS, "成功", null);
        }
    }
}
