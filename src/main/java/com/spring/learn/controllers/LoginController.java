package com.spring.learn.controllers;



//import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	 private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		 return new ModelAndView("/resources/index.html"); 
	}
	 
//	 @RequestMapping(value="/", method=RequestMethod.GET)
//	 public String main() {
//		 return "index";
//	 }
//	 @RequestMapping(value="/", method=RequestMethod.GET)
//	 public ModelAndView main() {
//		 return new ModelAndView("login", "user", new User());
//	 }
	 
//	 @RequestMapping(value="/check-user", method=RequestMethod.POST)
//	 public String checkUser(/*@Valid*/ @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
//		 if(bindingResult.hasErrors()) {
//			 return "login";
//		 }
//		 model.addAttribute("user", user);
//		 return "main";
//	 }
	 
////	 @RequestMapping(value="/failed", method=RequestMethod.GET)
//	 public ModelAndView failed() {
//		 return new ModelAndView("login-failed", "message", "Login Failed!");
//	 }
	 
	 
//	 @RequestMapping(value="get-json-user/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
//	 @RequestMapping(value="get-json-user/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
//	 @RequestMapping(value="get-json-user", method=RequestMethod.GET, produces="application/json")
//	 @ResponseBody
//	 public User getJsonUser(@PathVariable("name") String name) {
////	 public User getJsonUser(@RequestParam("name") String name) {
//		 User user = new User();
//		 user.setName(name);
//		 return user;
//	 }
	 
////	 @RequestMapping(value="put-json-user", method=RequestMethod.POST, consumes="application/json")
//	 public ResponseEntity<String> setJsonUser(@RequestBody User user){
//		 logger.info(user.getName());
//		 return new ResponseEntity<String>(HttpStatus.ACCEPTED);
//	 }
//	 
}
