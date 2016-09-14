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

import java.sql.Date;

import io.swagger.annotations.ApiModelProperty;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mykronoz.data.challenges.api.Challenges.ChallengesEnum;
import com.mykronoz.data.challenges.dao.DataDAO;
import com.mykronoz.libcommon.api.ClientInformation;
import com.mykronoz.libcommon.api.DayDateSerializer;
import com.mykronoz.libcommon.api.OffsetDateTimeDeserializer;
import com.mykronoz.libcommon.api.OffsetDateTimeSerializer;

public class ChallengeData {

    private int steps;
    private int distance;
    private int calories;
    private int sleepDuration;
    private int activityDuration;

    final static Logger logger = LoggerFactory.getLogger(ChallengeData.class);

    @JsonProperty("steps")
    @ApiModelProperty(value = "The number of steps")
    public void setSteps(int s) {
        this.steps = s;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getDistance() {
        return this.distance;
    }

    @JsonProperty("distance")
    @ApiModelProperty(value = "The distance in meter")
    public void setDistance(int d) {
        this.distance = d;
    }

    @JsonProperty("calories")
    @ApiModelProperty(value = "The calories in kcal")
    public void setCalories(int c) {
        this.calories = c;
    }

    public int getCalories() {
        return this.calories;
    }

    @JsonProperty("sleepDuration")
    @ApiModelProperty(value = "The sleep duration in minute")
    public int getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    @JsonProperty("activityDuration")
    @ApiModelProperty(value = "The activity duration in minute")
    public int getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(int activityDuration) {
        this.activityDuration = activityDuration;
    }

    public ChallengeData() {

    }

    public ChallengeData(int steps, int distance, int calories,
            int sleepDuration, int activityDuration) {
        super();
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
        this.sleepDuration = sleepDuration;
        this.activityDuration = activityDuration;
    }

    public static ChallengeData getRecord(DataDAO dao, Long uid) {
        ChallengeData data = dao.getRecords(uid);
        return data;
    }

}
