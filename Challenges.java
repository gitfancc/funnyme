/**************************************************************************/
/*                                                                        */
/*  Copyright (C) 2013-2016 Kronoz LLC                                    */
/*                                                                        */
/*  Should you receive a copy of this source code, you must check you     */
/*  have a proper, written authorization of Kronoz to hold it. If you     */
/*  don't have such an authorization, you must DELETE all source code     */
/*  files in your possession, and inform Kronoz of the fact you obtain    */
/*  these files. Should you not comply to these terms, you can be         */
/*  prosecuted in the extent permitted by applicable law.                 */
/*                                                                        */
/**************************************************************************/

package com.mykronoz.data.challenges.api;

import io.swagger.annotations.ApiModelProperty;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mykronoz.data.challenges.dao.DataDAO;
import com.mykronoz.libcommon.api.ClientInformation;
import com.mykronoz.libcommon.api.DayDateSerializer;

public class Challenges {
    final static Logger logger = LoggerFactory.getLogger(Challenges.class);

    ClientInformation info;
    private DateTime day;
    private ChallengeData values;
    private ChallengeData goals;

    @ApiModelProperty(value = "The activity reached values", position = 3)
    public ChallengeData getValues() {
        return values;
    }

    public void setValues(ChallengeData values) {
        this.values = values;
    }

    /**
     * @return the info
     */
    @ApiModelProperty(value = "The information about the submitter", position = 1)
    public ClientInformation getInfo() {
        return info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(ClientInformation info) {
        this.info = info;
    }

    @ApiModelProperty(value = "The day of the challenge", position = 2)
    @JsonSerialize(using = DayDateSerializer.class)
    public DateTime getDay() {
        return day;
    }

    public void setDay(DateTime day) {
        this.day = day;
    }

    @ApiModelProperty(value = "The goal values", position = 4)
    public ChallengeData getGoals() {
        return goals;
    }

    public void setGoals(ChallengeData goals) {
        this.goals = goals;
    }

    public enum ChallengesEnum {
        STEPS(0), DISTANCE(1), CAL(2), SLEEPDURATON(3), SPORTDURATION(4), ACTIVITYDURATION(
                5);
        private int val;

        private ChallengesEnum(int v) {
            this.val = v;
        }

        public static ChallengesEnum fromInt(int id) {
            for (ChallengesEnum type : ChallengesEnum.values()) {
                if (type.getValue() == id) {
                    return type;
                }
            }
            return null;
        }

        public int getValue() {
            return this.val;
        }
    };

    public void save(DataDAO dao, long uid) {
        String date = this.getDay().toString("YYYY-MM-dd");
        dao.addChallenge(uid, ChallengesEnum.STEPS.getValue(), date, this
                .getValues().getSteps(), this.getGoals().getSteps());
        dao.addChallenge(uid, ChallengesEnum.CAL.getValue(), date, this
                .getValues().getCalories(), this.getGoals().getCalories());
        dao.addChallenge(uid, ChallengesEnum.DISTANCE.getValue(), date, this
                .getValues().getDistance(), this.getGoals().getDistance());
        dao.addChallenge(uid, ChallengesEnum.SLEEPDURATON.getValue(), date,
                this.getValues().getSleepDuration(), this.getGoals()
                        .getSleepDuration());
        dao.addChallenge(uid, ChallengesEnum.SPORTDURATION.getValue(), date,
                this.getValues().getActivityDuration(), this.getGoals()
                        .getActivityDuration());

    }

    public void saveRecord(DataDAO dao, Long uid) {
        String date = this.getDay().toString("YYYY-MM-dd");
        dao.addRecord(uid, ChallengesEnum.STEPS.getValue(), date, this
                .getValues().getSteps());
        dao.addRecord(uid, ChallengesEnum.DISTANCE.getValue(), date, this
                .getValues().getDistance());
        dao.addRecord(uid, ChallengesEnum.CAL.getValue(), date, this
                .getValues().getCalories());
        dao.addRecord(uid, ChallengesEnum.SLEEPDURATON.getValue(), date, this
                .getValues().getSleepDuration());
        dao.addRecord(uid, ChallengesEnum.ACTIVITYDURATION.getValue(), date,
                this.getValues().getActivityDuration());
    }

    public static Challenges get(DataDAO dao, long uid, String day) {
        Challenges challenges = new Challenges();
        challenges.setValues(dao.getValues(uid, day));
        challenges.setGoals(dao.getGoals(uid, day));

        return challenges;
    }
}
