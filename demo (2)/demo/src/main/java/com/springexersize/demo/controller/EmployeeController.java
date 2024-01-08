package com.springexersize.demo.controller;

import com.springexersize.demo.model.Department;
import com.springexersize.demo.model.Employee;
import com.springexersize.demo.service.department.DepartmentService;
import com.springexersize.demo.service.employee.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
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


@RequestMapping("api/employee")

public class EmployeeController {
    private EmployeeService es;


    @Autowired

    public EmployeeController(EmployeeService es) {
        this.es = es;
    }
    @Autowired
    private DepartmentService ds;
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @PostMapping("/addemployee/{id}")
    public ResponseEntity<String> addEmployee(@RequestBody Employee e,@PathVariable int id){

        e.setDepartment(ds.getDepartmentById(id));
        es.addEmployee(e);
        return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getemployee/{id}")
    public ResponseEntity<Employee>getEmployee(@PathVariable int id){
        Employee e= es.getEmployeeById(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }


    @GetMapping("getall")
    public ResponseEntity <List<Employee>>getAllEmployees(){
        List<Employee> e=es.getAllEmployees();
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @DeleteMapping("deleteemployee/{id}")
    public ResponseEntity<String>deleteEmployee(@PathVariable int id){
        es.deleteEmployeeById(id);
        return new ResponseEntity<>("employee deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/updateemployee/{id}")
    public ResponseEntity<String>updateEmployee(@PathVariable int id,@RequestBody Employee e){
        es.updateEmployee(e,id);
        return new ResponseEntity<>("employee updated successfully", HttpStatus.OK);
    }

    @GetMapping("/showemployeeform")
    public String EmployeeRegisterForm(Model themodel){
        Employee e=new Employee();
        themodel.addAttribute("employee",e);
        return "employeesaveform";
    }

    @GetMapping("/saveform")
    public String EmployeeSaveForm(@Valid @ModelAttribute("employee") Employee e, BindingResult theBindingResult){
        String name=e.getDepartment().getName();
        String code=e.getDepartment().getCode();
        String description=e.getDepartment().getDescription();
        if(theBindingResult.hasErrors()){
            System.out.println("k");

            return "employeesaveform";
        }
        if(ds.getDepartmentByName(name)==null&&name!=null){
            System.out.println("ks");
            return "employeesaveform";
        }
        if(ds.getDepartmentByCode(code)==null&&code!=null){
            System.out.println("ks");
            return "employeesaveform";
        }

        if(ds.getDepartmentByDescription(description)==null&&description!=null){
            System.out.println("ks");
            return "employeesaveform";
        }



        else{




            Department d=ds.getDepartmentByName(name);
            e.setDepartment(d);
            es.addEmployee(e);
            return "employeesuccessform";
        }
    }
    @GetMapping("/showform")
    public String showEmployee(Model themodel){
        List<Employee>e=es.getAllEmployees();
        themodel.addAttribute("employees",e);
        return "employeeshowform";
    }
    @GetMapping("/searchform")
    public String searchEmployee(Model themodel){


        return "employeesearchform";
    }

    @GetMapping("/searchresult")
    public String EmployeeResult(Model themodel, HttpServletRequest request){

        String e=request.getParameter("code");
      Employee x=es.getEmployeeByCode(e);

        if(x==null&&e!=null){

            return "employeesearchform";
        }
        else{
           themodel.addAttribute("employeeresult",x);
           return "employeeresultsearchform";
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteEmployees(){
        es.deleteAll();

        return new ResponseEntity<>("deleted all successfully",HttpStatus.OK);
    }
}
