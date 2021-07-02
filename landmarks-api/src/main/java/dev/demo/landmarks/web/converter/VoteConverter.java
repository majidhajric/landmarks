package dev.demo.landmarks.web.converter;

import dev.demo.landmarks.entity.Vote;
import dev.demo.landmarks.web.dto.VoteRequest;

public class VoteConverter {

    public static Vote fromRequest(VoteRequest request) {
        Vote vote = new Vote();
        vote.setScore(request.getScore());
        return vote;
    }
}
