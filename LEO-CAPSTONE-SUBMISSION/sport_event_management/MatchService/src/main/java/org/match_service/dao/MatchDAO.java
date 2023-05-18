package org.match_service.dao;

import org.match_service.entity.*;
import org.match_service.pojo.MatchPojo;

import java.util.*;

public interface MatchDAO {

     List<MatchDetails> getAllMatches();
     Match getMatch(Integer matchID);
     List<MatchDetails> searchMatchesByParam(Integer matchID, Integer param);
     String deleteMatch(int matchID);
     List<Object> saveMatch(MatchPojo match, Integer matchID, String process);
     boolean isPlayerExist(String playerIDList);
     boolean isTeamExist(String teamIDList);
     List<MatchDetails> getMatchDetails(List<Match> matches);
}
