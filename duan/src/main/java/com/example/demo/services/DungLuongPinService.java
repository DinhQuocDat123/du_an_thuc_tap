package com.example.demo.services;

import com.example.demo.models.Chip;
import com.example.demo.models.DungLuongPin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DungLuongPinService {
    public Page<DungLuongPin> getAll(Pageable pageable);

    public Page<DungLuongPin> getAll1(Pageable pageable);

    public List<DungLuongPin> findAll();

    public List<DungLuongPin> findAll0();

    public DungLuongPin findById(UUID id);

    public DungLuongPin add(DungLuongPin dungLuongPin);

    public DungLuongPin update(UUID id, DungLuongPin dungLuongPin);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<DungLuongPin> search0(String ten);

    public List<DungLuongPin> search1(String ten);
}


