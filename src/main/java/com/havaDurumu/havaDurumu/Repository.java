package com.havaDurumu.havaDurumu;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface Repository extends JpaRepository<city, Long> {

    @Transactional
    void deleteByDone(boolean done);
}
