package iuh.htb.crudredishtb.repository;

import iuh.htb.crudredishtb.entity.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private static final String KEY = "employees";

    private HashOperations hashOps;
    private ListOperations<String, Employee> listOps;
    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
        this.listOps = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;
    }

    public void saveEmployee(Employee employee) {
//        hashOps.put("EMPLOYEE", employee.getId(), employee);
        listOps.leftPush(KEY, employee);
    }

    public List<Employee> findAll() {
        return hashOps.values(KEY);
    }

    public Employee findById(long id) {
        return (Employee) hashOps.get("EMPLOYEE", id);
    }

    public void update(Employee employee) {
        saveEmployee(employee);
    }

    public Employee getEmployeeAtIndex(Long index) {
        return listOps.index(KEY, index);
    }

    public void delete(long id) {
        hashOps.delete("EMPLOYEE", id);
    }

    public void deleteInList(Employee employee) {
        listOps.remove(KEY, 1, employee);
    }

    public long getNumberOfEmployees() {
        return listOps.size(KEY);
    }
}
