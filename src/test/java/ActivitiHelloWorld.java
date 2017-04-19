import com.tianzeng.react.SampleController;
/*import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;*/
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by tianzeng on 2017-04-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SampleController.class})
@WebAppConfiguration
@IntegrationTest
public class ActivitiHelloWorld {
   /* @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    *//**
     * 部署流程定义
     *//*
    @Test
    public void deployWorkFlowDefinition(){
        ProcessEngine processEngine =  ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService().
                createDeployment()
                .name("helloworld入门")
                .addClasspathResource("processes/helloworld.bpmn")
                .deploy();
        System.out.println("部署ID:"+deployment.getId());
        System.out.println("部署名:"+deployment.getName());
    }

    *//**
     * 启动流程实例
     *//*
    @Test
    public void startProcessInstance(){
        String key = "helloworld";
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(key);
        System.out.println("启动流程实例ID: "+pi.getId());
        System.out.println("流程定义IDID: "+pi.getProcessDefinitionId());
    }
    *//**
     * 查询个人当前任务
     *//*
    @Test
    public void findMyPersonTask(){
        String zhangsan = "张三";
        List<Task> zhangsanlist = taskService.createTaskQuery().taskAssignee(zhangsan).list();
        if(zhangsanlist != null && zhangsanlist.size() > 0){
            for(Task task:zhangsanlist){
                System.out.println("任务ID "+task.getId());
                System.out.println("任务名称 "+task.getName());
                System.out.println("任务创建时间 "+task.getCreateTime());
                System.out.println("任务办理人 "+task.getAssignee());
                System.out.println("流程实例ID "+task.getProcessInstanceId());
                System.out.println("流程对象ID "+task.getExecutionId());
                System.out.println("流程定义ID "+task.getProcessDefinitionId());
                System.out.println("-------------------------------------");
            }
        }

        String zhaosi = "李四";
        List<Task> zhaosilist = taskService.createTaskQuery().taskAssignee(zhaosi).list();
        if(zhaosilist != null && zhaosilist.size() > 0){
            for(Task task:zhaosilist){
                System.out.println("任务ID "+task.getId());
                System.out.println("任务名称 "+task.getName());
                System.out.println("任务创建时间 "+task.getCreateTime());
                System.out.println("任务办理人 "+task.getAssignee());
                System.out.println("流程实例ID "+task.getProcessInstanceId());
                System.out.println("流程对象ID "+task.getExecutionId());
                System.out.println("流程定义ID "+task.getProcessDefinitionId());
                System.out.println("-------------------------------------");
            }
        }

        String wangwu = "王五";
        List<Task> wangwuList = taskService.createTaskQuery().taskAssignee(wangwu).list();
        if(wangwuList != null && wangwuList.size() > 0){
            for(Task task:wangwuList){
                System.out.println("任务ID "+task.getId());
                System.out.println("任务名称 "+task.getName());
                System.out.println("任务创建时间 "+task.getCreateTime());
                System.out.println("任务办理人 "+task.getAssignee());
                System.out.println("流程实例ID "+task.getProcessInstanceId());
                System.out.println("流程对象ID "+task.getExecutionId());
                System.out.println("流程定义ID "+task.getProcessDefinitionId());
                System.out.println("-------------------------------------");
            }
        }


    }

    *//**
     * 完成我的任务
     *//*
    @Test
    public void completeMyPersonalTask(){
        String taskId = "7502";
        taskService.complete(taskId);
    }
    *//**
     * 查询流程定义
     *//*
    @Test
    public void findProcessDefinition(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        if(list != null && list.size() > 0){
            for(ProcessDefinition definition:list){
                System.out.println("流程定义ID "+definition.getId());
                System.out.println("流程定义名称 "+definition.getName());
                System.out.println("流程定义Key "+definition.getKey());
                System.out.println("定义版本"+definition.getVersion());
                System.out.println("资源名称"+definition.getResourceName());
                System.out.println("部署对象"+definition.getDeploymentId());
                System.out.println("-------------------------------------");
            }
        }
    }

    *//**
     * 删除流程定义
     *//*
    @Test
    public void deleteProcessDefinition(){
        String deploymentId = "12501";
        repositoryService.deleteDeployment(deploymentId,true);
    }

    *//**
     * 查看流程图片
     *//*
    @Test
    public void viewPic(){
        String deploymentId = "20001";
        List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
        String resName = "";
        if(list != null && list.size() > 0){
            for(String name:list){
                if(name.contains(".png")){
                    resName = name;
                }
            }
        }
        InputStream in = repositoryService.getResourceAsStream(deploymentId,resName);
        File file = new File("D:/"+resName);
        try {
            FileUtils.copyInputStreamToFile(in ,file);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
//    }

}
