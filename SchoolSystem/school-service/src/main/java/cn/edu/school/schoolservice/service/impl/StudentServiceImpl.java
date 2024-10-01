package cn.edu.school.schoolservice.service.impl;

import cn.edu.school.schoolservice.entity.Student;
import cn.edu.school.schoolservice.mapper.StudentMapper;
import cn.edu.school.schoolservice.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
