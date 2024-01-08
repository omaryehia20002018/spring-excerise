package com.springexersize.demo.controller;

import com.springexersize.demo.model.Department;
import com.springexersize.demo.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/department")
public class DepartmentController {

    private DepartmentService ds;

    @Autowired

    public DepartmentController(DepartmentService ds) {
        this.ds = ds;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/adddepartment")
    public ResponseEntity<String> addDepartment(@RequestBody Department d) {

        ds.addDepartment(d);
        return new ResponseEntity<>("saved successfully", HttpStatus.OK);
    }

    @GetMapping("/getdepartment/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable int id) {
        Department d = ds.getDepartmentById(id);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }


    @GetMapping("/getall")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> d = ds.getAllDepartments();
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @DeleteMapping("deletedepartment/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        ds.deleteDepartmentById(id);
        return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/updatedepartment/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable int id, @RequestBody Department d) {
        ds.updateDepartment(d, id);
        return new ResponseEntity<>("Department updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteDepartments(){
        ds.deleteAll();


        return new ResponseEntity<>("deleted all successfully",HttpStatus.OK);
    }

    @GetMapping("/showdepartmentform")
    public String DepartmentRegisterForm(Model themodel){
        Department d=new Department();
        themodel.addAttribute("department",d);
        return "departmentsaveform";
    }


    @GetMapping("/saveform")
    public String DepartmentSaveForm(@Valid @ModelAttribute("department") Department d, BindingResult theBindingResult){

        if(theBindingResult.hasErrors()){
            System.out.println("k");

            return "departmentsaveform";
        }




        else{




           ds.addDepartment(d);
            return "departmentsuccessform";
        }
    }

    @GetMapping("/showform")
    public String showDepartments(Model themodel){
        List<Department>d=ds.getAllDepartments();
        themodel.addAttribute("departments",d);
        return "departmentshowform";
    }


}
