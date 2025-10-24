package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.entities.WatchHistory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class WatchHistoryRepository implements PanacheRepositoryBase<WatchHistory, UUID> {
    public Optional<WatchHistory> findByProfileandMovie(UUID profileId, UUID movieId, UUID episodeId) {
        if (episodeId == null) {
            return find("profileId = ?1 and movieId = ?2 and episodeId is null", profileId, movieId).firstResultOptional();
        }
        return find("profileId = ?1 and movieId = ?2 and episodeId = ?3", profileId, movieId, episodeId).firstResultOptional();
    }
    
    public void saveOrUpdate(WatchHistory watchHistory) {
        UUID profileId = watchHistory.getProfile().getProfileId();
        UUID movieId = watchHistory.getMovie().getMovieId();
        UUID episodeId = watchHistory.getEpisode().getEpisodeId();
        
        Optional<WatchHistory> existingOpt = findByProfileandMovie(profileId, movieId, episodeId);
        
        if (existingOpt.isPresent()) {
            WatchHistory existing = existingOpt.get();
            existing.setWatchedSeconds(watchHistory.getWatchedSeconds());
            existing.setCompleted(watchHistory.isCompleted());
            existing.setWatchedAt(LocalDateTime.now());
            persist(existing);
        } else {
            WatchHistory newRecord = new WatchHistory();
            newRecord.setProfile(watchHistory.getProfile());
            newRecord.setMovie(watchHistory.getMovie());
            newRecord.setEpisode(watchHistory.getEpisode());
            newRecord.setWatchedSeconds(watchHistory.getWatchedSeconds());
            newRecord.setCompleted(watchHistory.isCompleted());
            newRecord.setWatchedAt(LocalDateTime.now());
            persist(newRecord);
        }
    }
}
