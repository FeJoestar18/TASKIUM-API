package com.taskium.project.Domain.Interfaces.Repository;

import com.taskium.project.Domain.Entity.UserAddress;

import java.util.List;

public interface IUserAddressRepository {
    UserAddress save(UserAddress userAddress);
    List<UserAddress> findByUserId(Long userId);
}

