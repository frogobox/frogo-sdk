package com.frogobox.coreutil.sport

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * TheSportDBApi
 * Copyright (C) 26/01/2020.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.frogoconsumeapi.sport.util
 *
 */

object SportData {

    object Sport {
        private const val URL_SPORT = "https://www.thesportsdb.com/api/v1/json/1/all_sports.php"

        const val ID_SPORT = "idSport"
        const val STR_SPORT = "strSport"
        const val STR_FORMAT = "strFormat"
        const val STR_SPORT_THUMB = "strSportThumb"
        const val STR_SPORT_DESCRIPTION = "strSportDescription"
    }

    object League {
        private const val URL_LEAGUE = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php"

        const val ID_LEAGUE = "idLeague"
        const val STR_LEAGUE = "strLeague"
        const val STR_SPORT = "strSport"
        const val STR_LEAGUE_ALTERNATE = "strLeagueAlternate"
    }

    object Country {
        private const val URL_COUNTRY =
            "https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?c=England&s=Soccer"

        const val ID_LEAGUE = "idLeague"
        const val ID_SOCCEL_XML = "idSoccerXML"
        const val ID_API_FOOTBALL = "idAPIfootball"
        const val STR_SPORT = "strSport"
        const val STR_LEAGUE = "strLeague"
        const val STR_LEAGUE_ALTERNATE = "strLeagueAlternate"
        const val STR_DIVISION = "strDivision"
        const val ID_CUP = "idCup"
        const val INT_FORMED_YEAR = "intFormedYear"
        const val DATE_FIRST_EVENT = "dateFirstEvent"
        const val STR_GENDER = "strGender"
        const val STR_COUNTRY = "strCountry"
        const val STR_WEBSITE = "strWebsite"
        const val STR_FACEBOOK = "strFacebook"
        const val STR_TWITTER = "strTwitter"
        const val STR_YOUTUBE = "strYoutube"
        const val STR_RSS = "strRSS"
        const val STR_DESCRIPTION_EN = "strDescriptionEN"
        const val STR_DESCRIPTION_DE = "strDescriptionDE"
        const val STR_DESCRIPTION_FR = "strDescriptionFR"
        const val STR_DESCRIPTION_IT = "strDescriptionIT"
        const val STR_DESCRIPTION_CN = "strDescriptionCN"
        const val STR_DESCRIPTION_JP = "strDescriptionJP"
        const val STR_DESCRIPTION_RU = "strDescriptionRU"
        const val STR_DESCRIPTION_ES = "strDescriptionES"
        const val STR_DESCRIPTION_PT = "strDescriptionPT"
        const val STR_DESCRIPTION_SE = "strDescriptionSE"
        const val STR_DESCRIPTION_NL = "strDescriptionNL"
        const val STR_DESCRIPTION_HU = "strDescriptionHU"
        const val STR_DESCRIPTION_NO = "strDescriptionNO"
        const val STR_DESCRIPTION_PL = "strDescriptionPL"
        const val STR_DESCRIPTION_IL = "strDescriptionIL"
        const val STR_FANART_1 = "strFanart1"
        const val STR_FANART_2 = "strFanart2"
        const val STR_FANART_3 = "strFanart3"
        const val STR_FANART_4 = "strFanart4"
        const val STR_BANNER = "strBanner"
        const val STR_BADGE = "strBadge"
        const val STR_LOGO = "strLogo"
        const val STR_POSTER = "strPoster"
        const val STR_TROPHY = "strTrophy"
        const val STR_NAMING = "strNaming"
        const val STR_COMPLETE = "strComplete"
        const val STR_LOCKED = "strLocked"
    }

    object Season {
        private const val URL_SEASON =
            "https://www.thesportsdb.com/api/v1/json/1/search_all_seasons.php?id=4328"

        const val STR_SEASON = "strSeason"
    }

    object Team {
        private const val URL_TEAM =
            "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"

        const val ID_TEAM = "idTeam"
        const val ID_SOCCER_XML = "idSoccerXML"
        const val ID_API_FOOTBALL = "idAPIfootball"
        const val INT_LOVED = "intLoved"
        const val STR_TEAM = "strTeam"
        const val STR_TEAM_SHORT = "strTeamShort"
        const val STR_ALTERNATE = "strAlternate"
        const val INT_FORMED_YEAR = "intFormedYear"
        const val STR_SPORT = "strSport"
        const val STR_LEAGUE = "strLeague"
        const val ID_LEAGUE = "idLeague"
        const val STR_DIVISION = "strDivision"
        const val STR_MANAGER = "strManager"
        const val STR_STADIUM = "strStadium"
        const val STR_KEYWORD = "strKeywords"
        const val STR_RSS = "strRSS"
        const val STR_STADIUM_THUMB = "strStadiumThumb"
        const val STR_STADIUM_DESCRIPTION = "strStadiumDescription"
        const val STR_STADIUM_LOCATION = "strStadiumLocation"
        const val STR_STADIUM_CAPACITY = "intStadiumCapacity"
        const val STR_WEBSITE = "strWebsite"
        const val STR_FACEBOOK = "strFacebook"
        const val STR_TWITTER = "strTwitter"
        const val STR_INSTAGRAM = "strInstagram"
        const val STR_DESCRIPTION_EN = "strDescriptionEN"
        const val STR_DESCRIPTION_DE = "strDescriptionDE"
        const val STR_DESCRIPTION_FR = "strDescriptionFR"
        const val STR_DESCRIPTION_IT = "strDescriptionIT"
        const val STR_DESCRIPTION_CN = "strDescriptionCN"
        const val STR_DESCRIPTION_JP = "strDescriptionJP"
        const val STR_DESCRIPTION_RU = "strDescriptionRU"
        const val STR_DESCRIPTION_ES = "strDescriptionES"
        const val STR_DESCRIPTION_PT = "strDescriptionPT"
        const val STR_DESCRIPTION_SE = "strDescriptionSE"
        const val STR_DESCRIPTION_NL = "strDescriptionNL"
        const val STR_DESCRIPTION_HU = "strDescriptionHU"
        const val STR_DESCRIPTION_NO = "strDescriptionNO"
        const val STR_DESCRIPTION_PL = "strDescriptionPL"
        const val STR_DESCRIPTION_IL = "strDescriptionIL"
        const val STR_GENDER = "strGender"
        const val STR_COUNTRY = "strCountry"
        const val STR_TEAM_BADGE = "strTeamBadge"
        const val STR_TEAM_JERSEY = "strTeamJersey"
        const val STR_TEAM_LOGO = "strTeamLogo"
        const val STR_TEAM_FANART_1 = "strTeamFanart1"
        const val STR_TEAM_FANART_2 = "strTeamFanart2"
        const val STR_TEAM_FANART_3 = "strTeamFanart3"
        const val STR_TEAM_FANART_4 = "strTeamFanart4"
        const val STR_TEAM_BANNER = "strTeamBanner"
        const val STR_YOUTUBE = "strYoutube"
        const val STR_LOCKED = "strLocked"
    }

    object Player {

        const val ID_PLAYER = "idPlayer"
        const val ID_TEAM = "idTeam"
        const val ID_TEAM_2 = "idTeam2"
        const val ID_TEAM_NATIONAL = "idTeamNational"
        const val ID_SOCCER_XML = "idSoccerXML"
        const val ID_API_FOOTBALL = "idAPIfootball"
        const val ID_PLAYER_MANAGER = "idPlayerManager"
        const val ID_STR_NATIONALITY = "strNationality"
        const val STR_PLAYER = "strPlayer"
        const val STR_TEAM = "strTeam"
        const val STR_TEAM_2 = "strTeam2"
        const val STR_SPORT = "strSport"
        const val INT_SOCCER_XML_TEAM_ID = "intSoccerXMLTeamID"
        const val DATE_BORN = "dateBorn"
        const val STR_NUMBER = "strNumber"
        const val DATE_SIGNED = "dateSigned"
        const val STR_SIGNING = "strSigning"
        const val STR_WAGE = "strWage"
        const val STR_OUTFITTER = "strOutfitter"
        const val STR_KIT = "strKit"
        const val STR_AGENT = "strAgent"
        const val STR_BIRTH_LOCATION = "strBirthLocation"
        const val STR_DESCRIPTION_EN = "strDescriptionEN"
        const val STR_DESCRIPTION_DE = "strDescriptionDE"
        const val STR_DESCRIPTION_FR = "strDescriptionFR"
        const val STR_DESCRIPTION_IT = "strDescriptionIT"
        const val STR_DESCRIPTION_CN = "strDescriptionCN"
        const val STR_DESCRIPTION_JP = "strDescriptionJP"
        const val STR_DESCRIPTION_RU = "strDescriptionRU"
        const val STR_DESCRIPTION_ES = "strDescriptionES"
        const val STR_DESCRIPTION_PT = "strDescriptionPT"
        const val STR_DESCRIPTION_SE = "strDescriptionSE"
        const val STR_DESCRIPTION_NL = "strDescriptionNL"
        const val STR_DESCRIPTION_HU = "strDescriptionHU"
        const val STR_DESCRIPTION_NO = "strDescriptionNO"
        const val STR_DESCRIPTION_IL = "strDescriptionIL"
        const val STR_DESCRIPTION_PL = "strDescriptionPL"
        const val STR_GENDER = "strGender"
        const val STR_SIDE = "strSide"
        const val STR_POSITION = "strPosition"
        const val STR_COLLEGE = "strCollege"
        const val STR_FACEBOOK = "strFacebook"
        const val STR_WEBSITE = "strWebsite"
        const val STR_TWITTER = "strTwitter"
        const val STR_INSTAGRAM = "strInstagram"
        const val STR_YOUTUBE = "strYoutube"
        const val STR_HEIGHT = "strHeight"
        const val STR_WEIGHT = "strWeight"
        const val INT_LOVED = "intLoved"
        const val STR_THUMB = "strThumb"
        const val STR_CUTOUT = "strCutout"
        const val STR_RENDER = "strRender"
        const val STR_BANNER = "strBanner"
        const val STR_FANART_1 = "strFanart1"
        const val STR_FANART_2 = "strFanart2"
        const val STR_FANART_3 = "strFanart3"
        const val STR_FANART_4 = "strFanart4"
        const val STR_CREATIVE_COMMONS = "strCreativeCommons"
        const val STR_LOCKED = "strLocked"

    }

    object Event {

        const val ID_EVENT = "idEvent"
        const val ID_SOCCER_XML = "idSoccerXML"
        const val ID_API_FOOTBALL = "idAPIfootball"
        const val STR_EVENT = "strEvent"
        const val STR_EVENT_ALTERNATE = "strEventAlternate"
        const val STR_FILENAME = "strFilename"
        const val STR_SPORT = "strSport"
        const val ID_LEAGUE = "idLeague"
        const val STR_LEAGUE = "strLeague"
        const val STR_SEASON = "strSeason"
        const val STR_DESCRIPTION_EN = "strDescriptionEN"
        const val STR_HOME_TEAM = "strHomeTeam"
        const val STR_AWAY_TEAM = "strAwayTeam"
        const val INT_HOME_SCORE = "intHomeScore"
        const val INT_ROUND = "intRound"
        const val INT_AWAY_SCORE = "intAwayScore"
        const val INT_SPECTATORS = "intSpectators"
        const val STR_HOME_GOAL_DETAILS = "strHomeGoalDetails"
        const val STR_HOME_RED_CARDS = "strHomeRedCards"
        const val STR_HOME_YELLOW_CARDS = "strHomeYellowCards"
        const val STR_HOME_LINEUP_GOALKEEPER = "strHomeLineupGoalkeeper"
        const val STR_HOME_LINEUP_DEFENSE = "strHomeLineupDefense"
        const val STR_HOME_LINEUP_MIDFIELD = "strHomeLineupMidfield"
        const val STR_HOME_LINEUP_FORWARD = "strHomeLineupForward"
        const val STR_HOME_LINEUP_SUBTITUTES = "strHomeLineupSubstitutes"
        const val STR_HOME_FORMATION = "strHomeFormation"
        const val STR_AWAY_RED_CARDS = "strAwayRedCards"
        const val STR_AWAY_YELLOW_CARDS = "strAwayYellowCards"
        const val STR_AWAY_GOAL_DETAILS = "strAwayGoalDetails"
        const val STR_AWAY_LINEUP_GOALKEEPER = "strAwayLineupGoalkeeper"
        const val STR_AWAY_LINEUP_DEFENSE = "strAwayLineupDefense"
        const val STR_AWAY_LINEUP_MIDFIELD = "strAwayLineupMidfield"
        const val STR_AWAY_LINEUP_FORWARD = "strAwayLineupForward"
        const val STR_AWAY_LINEUP_SUBTITUTES = "strAwayLineupSubstitutes"
        const val STR_AWAY_FORMATION = "strAwayFormation"
        const val INT_HOME_SHOTS = "intHomeShots"
        const val INT_AWAY_SHOTS = "intAwayShots"
        const val DATE_EVENT = "dateEvent"
        const val DATE_EVENT_LOCAL = "dateEventLocal"
        const val STR_DATE = "strDate"
        const val STR_TIME = "strTime"
        const val STR_TIME_LOCAL = "strTimeLocal"
        const val STR_TV_STATION = "strTVStation"
        const val ID_HOME_TEAM = "idHomeTeam"
        const val ID_AWAY_TEAM = "idAwayTeam"
        const val STR_RESULT = "strResult"
        const val STR_CIRCUIT = "strCircuit"
        const val STR_COUNTRY = "strCountry"
        const val STR_CITY = "strCity"
        const val STR_POSTER = "strPoster"
        const val STR_FANART = "strFanart"
        const val STR_THUMB = "strThumb"
        const val STR_BANNER = "strBanner"
        const val STR_MAP = "strMap"
        const val STR_TWEET_1 = "strTweet1"
        const val STR_TWEET_2 = "strTweet2"
        const val STR_TWEET_3 = "strTweet3"
        const val STR_VIDEO = "strVideo"
        const val STR_LOCKED = "strLocked"

    }

    object User {
        const val ID_EDIT = "idEdit"
        const val STR_USERNAME = "strUsername"
        const val STR_EDIT_TYPE = "strEditType"
        const val STR_REASON = "strReason"
        const val DATE = "date"
        const val ID_TEAM = "idTeam"
        const val ID_PLAYER = "idPlayer"
        const val ID_LEAGUE = "idLeague"
        const val ID_EVENT = "idEvent"
        const val STR_TEAM = "strTeam"
        const val STR_PLAYER = "strPlayer"
        const val STR_LEAGUE = "strLeague"
        const val STR_EVENT = "strEvent"
        const val STR_EVENT_POSTER = "strEventPoster"
        const val STR_PLAYER_TUMB = "strPlayerThumb"
        const val STR_TEAM_BADGE = "strTeamBadge"
    }

    object Honor {

        const val ID = "id"
        const val ID_PLAYER = "idPlayer"
        const val ID_TEAM = "idTeam"
        const val STR_SPORT = "strSport"
        const val STR_PLAYER = "strPlayer"
        const val STR_TEAM = "strTeam"
        const val STR_HONOUR = "strHonour"
        const val STR_SEASON = "strSeason"

    }

    object FormerTeam {

        const val ID = "id"
        const val ID_PLAYER = "idPlayer"
        const val ID_FORMER_TEAM = "idFormerTeam"
        const val STR_SPORT = "strSport"
        const val STR_PLAYER = "strPlayer"
        const val STR_FORMER_TEAM = "strFormerTeam"
        const val STR_TEAM_BADGE = "strTeamBadge"
        const val STR_JOINED = "strJoined"
        const val STR_DEPARTED = "strDeparted"

    }

    object Contract {
        const val ID = "id"
        const val ID_PLAYER = "idPlayer"
        const val ID_TEAM = "idTeam"
        const val STR_SPORT = "strSport"
        const val STR_PLAYER = "strPlayer"
        const val STR_TEAM = "strTeam"
        const val STR_TEAM_BADGE = "strTeamBadge"
        const val STR_YEAR_START = "strYearStart"
        const val STR_YEAR_END = "strYearEnd"
        const val STR_WAGE = "strWage"
    }

    object Table {
        const val NAME = "name"
        const val TEAM_ID = "teamid"
        const val PLAYED = "played"
        const val GOALS_FOR = "goalsfor"
        const val GOALS_AGAINST = "goalsagainst"
        const val GOALS_DIFFERENCE = "goalsdifference"
        const val WIN = "win"
        const val DRAW = "draw"
        const val LOSS = "loss"
        const val TOTAL = "total"
    }

}
