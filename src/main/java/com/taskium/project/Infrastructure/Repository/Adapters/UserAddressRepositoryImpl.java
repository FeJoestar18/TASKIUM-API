package com.taskium.project.Infrastructure.Repository.Adapters;
import com.taskium.project.Domain.Entity.UserAddress;
import com.taskium.project.Domain.Interfaces.Repository.IUserAddressRepository;
import com.taskium.project.Infrastructure.Repository.JPA.UserAddressJpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class UserAddressRepositoryImpl implements IUserAddressRepository {
    private final UserAddressJpaRepository jpaRepository;
    public UserAddressRepositoryImpl(UserAddressJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public UserAddress save(UserAddress userAddress) {
        return jpaRepository.save(userAddress);
    }
    @Override
    public List<UserAddress> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }
}
