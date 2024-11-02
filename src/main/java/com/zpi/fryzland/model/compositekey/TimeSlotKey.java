package com.zpi.fryzland.model.compositekey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeSlotKey implements Serializable {
    private Date timeSlotDate;
    private Time timeSlotTime;
}
