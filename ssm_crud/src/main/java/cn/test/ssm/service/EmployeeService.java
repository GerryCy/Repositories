package cn.test.ssm.service;

import cn.test.ssm.bean.Employee;
import cn.test.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /*
        查询员工列表信息
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleAndDepartment(null);
    }
}
