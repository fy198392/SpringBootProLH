package com.hanwintech.project.service;

import java.util.List;

import com.hanwintech.project.entity.TestTable;

/**   
 *  
 * @ProjectName: project
 * @Package: com.hanwintech.project.service    
 * @ClassName: TestServiceMbatisService     
 * @Description:  测试接口
 * @Author:  lee       
 * @CreateDate:  2016/11/21    
 * @UpdateUser:      
 * @UpdateDate:      
 * @UpdateRemark: 
 * @Version:  v0.0.1     
 *    
 */
public interface TestServiceMbatisService {
	public boolean addTestTableByMabtis();	
	public List<TestTable> likeName(String name);
	public void test(String username);
}
