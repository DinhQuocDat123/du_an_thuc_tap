package com.example.demo.services;

import com.example.demo.models.Chip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChipService {
    public Page<Chip> getAll(Pageable pageable);

    public Page<Chip> getAll1(Pageable pageable);

    public List<Chip> findAll();

    public List<Chip> findAll0();

    public Chip findById(UUID id);

    public Chip add(Chip chip);

    public Chip update(UUID id, Chip chip);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<Chip> search0(String ten);

    public List<Chip> search1(String ten);
}

