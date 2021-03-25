package com.diseno.demo.controller;

import com.diseno.demo.request.test.Patient;
import com.diseno.demo.response.common.NewDataResponse;
import com.diseno.demo.service.PatientService;
import com.diseno.demo.unlti.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * date 2021-03-24 11:24
 *
 * @author Phạm Ngọc Thắng
 */

@RestController
@RequestMapping("/web/test")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/getPatientDetails")
    public ResponseEntity getPatient(@RequestParam String name ) throws InterruptedException, ExecutionException{
        RequestUtils.getFirstRequest(name);
        Patient patient = patientService.getPatientDetails(name);
        return NewDataResponse.setDataSearch(patient);
    }

    @PostMapping("/createPatient")
    public ResponseEntity createPatient(@RequestBody Patient patient ) throws InterruptedException, ExecutionException {
       String response =  patientService.savePatientDetails(patient);
        return NewDataResponse.setDataCreate(response);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity updatePatient(@RequestBody Patient patient  ) throws InterruptedException, ExecutionException {
        String response = patientService.updatePatientDetails(patient);
        return NewDataResponse.setDataUpdate(response);
    }

    @DeleteMapping("/deletePatient")
    public ResponseEntity deletePatient(@RequestParam String name){
        String response = patientService.deletePatient(name);
        return NewDataResponse.setDataDelete(response);
    }
}
