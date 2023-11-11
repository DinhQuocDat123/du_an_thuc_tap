package com.example.demo.services;

import com.example.demo.models.Chip;
import com.example.demo.models.DungLuongPin;
import com.example.demo.models.Pin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PinService {
    public Page<Pin> getAll(Pageable pageable);

    public Page<Pin> getAll1(Pageable pageable);

    public List<Pin> findAll();

    public List<Pin> findAll0();

    public Pin findById(UUID id);

    public Pin add(Pin pin);

    public Pin update(UUID id, Pin pin);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<Pin> search0(String ten);

    public List<Pin> search1(String ten);
}


