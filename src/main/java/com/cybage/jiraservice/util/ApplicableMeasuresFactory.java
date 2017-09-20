package com.cybage.jiraservice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cybage.jiraservice.common.ApplicableMeasureConstant;


@Component
@Scope("singleton")
public class ApplicableMeasuresFactory {

	private static Map<Integer,String> mapMeasure = new HashMap<>();
	
	public ApplicableMeasuresFactory() {
		
		ApplicableMeasuresFactory.mapMeasure.put(1, ApplicableMeasureConstant.COUNT.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(2, ApplicableMeasureConstant.SIZE.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(4, ApplicableMeasureConstant.FREQUENCY.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(8, ApplicableMeasureConstant.EFFORTS.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(16, ApplicableMeasureConstant.EXECUTIONUNIT.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(32, ApplicableMeasureConstant.EXISTS.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(64, ApplicableMeasureConstant.ACTOR.getValue());
		ApplicableMeasuresFactory.mapMeasure.put(128, ApplicableMeasureConstant.USER.getValue());
		
	}

	public static final ArrayList<String> getApplicableMeasuresList(int number){ 
		ArrayList<Integer> partition = new ArrayList<Integer>();
		ArrayList<String> applicableMeasure = new ArrayList<String>();
        int tmp;
        if (number == 0) {
            partition.add(Integer.valueOf(0));
        }

        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            tmp = mask & number;
            if (tmp != 0) {
            	partition.add(Integer.valueOf(tmp));
            }
        }
        
        
		for(int i=0 ; i< partition.size(); i++){
			applicableMeasure.add(mapMeasure.get(partition.get(i)));
		}
			
		return applicableMeasure;
        
	}
}
