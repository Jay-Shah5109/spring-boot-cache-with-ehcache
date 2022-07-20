package com.javacode.cachemechanism.service;

import com.javacode.cachemechanism.model.Address;
import com.javacode.cachemechanism.model.Major;
import com.javacode.cachemechanism.model.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class APIService {
    // Key should be same as parameter value (in below case, see 'studentID')

    @CacheEvict(value = "twenty-second-cache", key = "'StudentCache'+#studentID",
            condition = "#isCacheable != null && !#isCacheable", beforeInvocation = true)
    @Cacheable(value = "twenty-second-cache", key = "'StudentCache'+#studentID",
            condition = "#isCacheable != null && #isCacheable")
    public Optional<Student> fetchStudent(String studentID, boolean isCacheable) throws InterruptedException {
        Thread.sleep(4000);
        return Arrays.asList((new Student("19VC21", "John", "Wayne", "11",
                        new Address("12A", "Bay Avenue", "SanFrancisco", "CA", "91234"),
                                Major.MATHS)),
                new Student("19VC22", "Mary", "Jane", "11",
                        new Address("10A", "Cross Avenue", "SanFrancisco", "CA", "91238")
                        , Major.CHEMISTRY),
                new Student("19VC23", "Peter", "Parker", "11",
                        new Address("1A", "First Avenue", "SanFrancisco", "CA", "91934")
                        , Major.PHYSICS))
                .stream().filter(student -> student.getId().equalsIgnoreCase(studentID)).findFirst();
    }
}
