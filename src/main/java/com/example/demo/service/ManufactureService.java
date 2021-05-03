package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.models.Manufacture;
import com.example.demo.repozitory.ManufactureRepozitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Component
@EnableAspectJAutoProxy
public class ManufactureService implements ManufactureServiceInterface{
    @Autowired
    private ManufactureRepozitory manufactureRepozitory;

    private final Logger log = LoggerFactory.getLogger(ManufactureService.class);

    @Override
    public void addManufacture(Manufacture manufacture) {
        log.info("Added new manufacture {}", manufacture);
        manufactureRepozitory.save(manufacture);
    }

    @Override
    public List<Manufacture> getAllManufacture() {
        log.info("Found all manufactures");
        List<Manufacture> manufactures = manufactureRepozitory.findAll();
        return manufactures;
    }

    @Override
    public List<Manufacture> filterByName() {
        log.info("Sorted list of manufactures by name");
        return manufactureRepozitory.findByOrderByName();
    }

    @Override
    public List<Manufacture> filterByAddress() {
        log.info("Sorted list of manufactures by address");
        return manufactureRepozitory.findByOrderByAddress();
    }

    @Override
    public List<Manufacture> filterByManId() {
        log.info("Sorted list of manufactures by id");
        return manufactureRepozitory.findByOrderById();
    }

    @Override
    public Manufacture getManufactureById(Long id) {
        log.info("Found manufacture by id {}", id);
        return manufactureRepozitory.getOne(id);
    }

    @Override
    public void deleteManufactureById(Long id) {
        log.warn("Trying to delete manufacture with id {}", id);
        try {
            manufactureRepozitory.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("Manufacture has linked workers! You cant remove it!");
        }
    }

    @Override
    public void deleteAllManufactures() {
        log.info("Deleted all manufactures");
        manufactureRepozitory.deleteAll();
    }


    @Override
    public Long getManufactureId(String name) {
        log.info("Found manufacture id with name {}", name);
        return manufactureRepozitory.findByName(name).getId();
    }

    @Override
    public String printManufactures(List<Manufacture> list) {
        String buff = "<h>Manufactures</h><br/>";
        int i = 0;
        for (Manufacture item: list) {
            buff += "<tr><td>"+item.getId()+" - " + item.getName() + "  " + item.getAddress() + "<br/></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>Manufacture list is empty</td></tr>";
        }
        i = 0;
        return buff;
    }
}
