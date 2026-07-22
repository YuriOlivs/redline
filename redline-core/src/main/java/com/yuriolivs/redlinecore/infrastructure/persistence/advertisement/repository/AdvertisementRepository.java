package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.repository;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.AdvertisementEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.SavedAdvertisementEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.mapper.AdvertisementPersistenceMapper;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.mapper.SavedAdvertisementPersistenceMapper;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdvertisementRepository implements IAdvertisementRepository, ISavedAdvertisementRepository {
    private final AdvertisementJpaRepository adJpaRepository;
    private final SavedAdvertisementJpaRepository savedAdJpaRepository;
    private final AdvertisementPersistenceMapper mapper;
    private final SavedAdvertisementPersistenceMapper savedAdMapper;
    private final UserPersistenceMapper userMapper;

    @Override
    public Advertisement save(Advertisement ad) {
        AdvertisementEntity adSaved = adJpaRepository.save(mapper.toEntity(ad));
        return mapper.toDomain(adSaved);
    }

    @Override
    public List<Advertisement> saveAll(List<Advertisement> ads) {
        List<AdvertisementEntity> adsSaved = adJpaRepository.saveAll(
                ads.stream().map(mapper::toEntity).toList()
        );

        return adsSaved.stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Advertisement> findByUrl(String url) {
        return adJpaRepository.findByUrl(url).map(mapper::toDomain);
    }

    @Override
    public List<Advertisement> findUnsavedOlderThan(LocalDate threshold) {
        return adJpaRepository.findUnsavedOlderThen(threshold)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Advertisement> findBySearchCriteria(AdvertisementSearchCriteria criteria) {
        Pageable pageable = PageRequest.of(
                criteria.getPage(),
                criteria.getSize(),
                Sort.by("createdAt").descending()
        );

        return adJpaRepository.findBySearchCriteria(
                criteria.getBrand(),
                criteria.getModel(),
                criteria.getYear(),
                criteria.getWebsite(),
                criteria.getMileage(),
                pageable
        ).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void remove(Advertisement ad) {
        adJpaRepository.delete(mapper.toEntity(ad));
    }

    @Override
    public void removeAll(List<Advertisement> ads) {
        adJpaRepository.deleteAll(ads.stream().map(mapper::toEntity).toList());
    }

    @Override
    public SavedAdvertisement save(SavedAdvertisement savedAd) {
        SavedAdvertisementEntity adSaved = savedAdJpaRepository.save(savedAdMapper.toEntity(savedAd));
        return savedAdMapper.toDomain(adSaved);
    }

    @Override
    public List<SavedAdvertisement> findByUser(User user) {
        return savedAdJpaRepository.findByUserId(user.getId())
                .stream()
                .map(savedAdMapper::toDomain)
                .toList();
    }

    @Override
    public List<User> findUsersByAdvertisement(String url) {
        return savedAdJpaRepository.findUsersByAdvertisement(url)
                .stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public void remove(SavedAdvertisement savedAd) {
        savedAdJpaRepository.delete(savedAdMapper.toEntity(savedAd));
    }

    @Override
    public void removeAllByUser(User user) {
        savedAdJpaRepository.deleteAllByUser(user.getId());
    }
}
