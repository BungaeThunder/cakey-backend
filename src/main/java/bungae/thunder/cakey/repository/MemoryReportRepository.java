package bungae.thunder.cakey.repository;

import bungae.thunder.cakey.domain.Report;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryReportRepository implements ReportRepository{
    private static Map<Long, Report> store = new HashMap<>();

    private static long sequence = 0L;

    @Override
    public Report save(Report report) {
        report.setId(++sequence);
        store.put(report.getId(), report);
        return report;
    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Report> findAllByMessageId(Long messageId) {
        return store.values().stream()
                .filter(report -> report.getMessageId().equals(messageId) )
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() { store.clear(); }
}

