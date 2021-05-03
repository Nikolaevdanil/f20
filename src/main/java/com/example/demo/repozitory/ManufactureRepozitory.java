package com.example.demo.repozitory;


import com.example.demo.models.Manufacture;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@EnableAspectJAutoProxy
public interface ManufactureRepozitory extends JpaRepository<Manufacture, Long> {
    List<Manufacture> findByOrderByName();
    List<Manufacture> findByOrderByAddress();
    List<Manufacture> findByOrderById();
    Manufacture findByName(String name);
}
