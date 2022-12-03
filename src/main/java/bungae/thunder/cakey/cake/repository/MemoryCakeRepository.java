package bungae.thunder.cakey.cake.repository;

import bungae.thunder.cakey.cake.domain.Cake;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryCakeRepository implements CakeRepository {

    private static Map<Long, Cake> store = new HashMap<>();

    private static long sequence = 0L;

    @Override
    public Cake save(Cake cake) {
        cake.setId(++sequence);
        store.put(cake.getId(), cake);
        return cake;
    }

    @Override
    public Optional<Cake> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Cake> findOneByUserId(Long userId) {
        return store.values().stream()
                .filter(cake -> cake.getUserId().equals(userId))
                .max(Comparator.comparingInt(Cake::getYear));
    }

    @Override
    public List<Cake> findAllByUserId(Long userId) {
        return store.values().stream()
                .filter(cake -> cake.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cake> findByUserIdAndYear(Long userId, Integer year) {
        return store.values().stream()
                .filter(cake -> cake.getUserId().equals(userId) && cake.getYear().equals(year))
                .findFirst();
    }

    @Override
    public List<Cake> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
