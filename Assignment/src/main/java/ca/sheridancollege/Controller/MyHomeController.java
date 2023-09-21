package ca.sheridancollege.Controller;

import lombok.*;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.Bean.DogBean;
import ca.sheridancollege.Bean.HandlerBean;
import ca.sheridancollege.DAO.DAO;
import ca.sheridancollege.Services.NotificationService;

@Controller
public class MyHomeController {
	

	@Autowired
	protected JavaMailSender javaMailSemder;
	
	
	@GetMapping("/")
	public String goAddHome() {
		return "index.html";
		
	}
	
	
	@GetMapping("/addDog")
	public String gotoAddDog() {
		
		return "addDog.html";
	}

	
	@GetMapping("/aboutus")
	public String gotoAbout() {
		return "Aboutus.html";
	}

	@GetMapping("/displayindi")
	public String gotoDogs() {
		return "IndividualShow.html";
		
	}
	
	@GetMapping("/contactus")
	public String gotoContact() {
		return "contactus.html";
		
	}
	
	@GetMapping("/doghandlerreg")
	public String gotoRegister(Model model) {
		model.addAttribute("dogList", DAO.showDogs());

		return "handlerRegi.html";
	}
	
	
public static String encyptPassword(String  password) {
		
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	@GetMapping("/hander")
	public String Handler(Model model, @RequestParam String entry_Number,
			@RequestParam String hu,
			@RequestParam String hp,
			@RequestParam String number,
			@RequestParam String email) {
		
		HandlerBean hb = null;
		String[] arr = entry_Number.split(",");

		for(String ss : arr){
			
			System.out.println(ss);

			hb = new HandlerBean(ss,hu,number,email);
			DAO.addHandler(hb,encyptPassword(hp));
		}
		
		try {
			
			NotificationService notificationService = new NotificationService(javaMailSemder);
		 notificationService.SendNotification(hb);
		 
			
		}
		
		catch(MailException e) {
			
		}
		
	
		return "handlerRegi.html";
	}
	
	
	
	
	
	@GetMapping("/hanlerPage")
	public String goToLogin(Authentication authentication, Model model) {
		
		String name = authentication.getName();
		ArrayList<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga :authentication.getAuthorities()) {
			
			roles.add(ga.getAuthority());

		}
		
		model.addAttribute("userName",name);
		model.addAttribute("viewHandlerDogs", DAO.dogsHandleByHandler(name));
		
		 return "/handler/handlerPage.html";
	}
	
	
	@GetMapping("/login")
	public String loginpage() {
		
		
		
		return "login.html";
	}

	@GetMapping("/add")
	public String addDog(@RequestParam String dogName,
			@RequestParam String OwnerName,
			@RequestParam String owneremail,
			@RequestParam String breed,
			@RequestParam String group,
			@RequestParam String gender,
			@RequestParam String ranking,
			@RequestParam String date,
			Model model
			) {
	
		
		if(!dogName.isEmpty()&&!OwnerName.isEmpty()&&!breed.isEmpty()&&!group.isEmpty()&&!gender.isEmpty()&&!ranking.isEmpty()&&!owneremail.isEmpty())
		{
			DogBean b = null;
		Random rand = new Random();
			int rand_int1 = rand.nextInt(9999); 
			System.out.println(date);
			
			String[] arr = date.split(",");
			
			for(String ss : arr){
				
		b = new DogBean(rand_int1,dogName,OwnerName,owneremail,breed,group, gender, ranking, ss);
			DAO.addDog(b);
			
			System.out.println(b);
			
			}
			
			try {
				
				NotificationService notificationService = new NotificationService(javaMailSemder);
			 notificationService.SendNotificationforaddingDog(b);
			 
				
			}
			
			catch(MailException e) {
				
			}
	
		DogBean be = new DogBean();
		String error = be.getError();
		
		if (error == null) {
			String m = "Record successfully added";
			model.addAttribute("message",m);
			//model.addAttribute("key", rand_int1);
		}
		
		else {
			String m = "Please try again, we are not able to generate unique entry for this record";
			model.addAttribute("errorMessage");
			
		}
		}
		
		else {
			
			String m = "Field should not be empty";
			model.addAttribute("Error",m);
			
		}

		return "addDog.html";
		
	}
	
	
	@GetMapping("/delete/{id}") 
	public String deleteDog(

	@PathVariable int id, Model model) {

	DAO.deleteDog(id);
	model.addAttribute("dogList", DAO.showDogs());


	return "view.html";


	}

	@GetMapping("/edit/{id}") 
	public String editDog
	(@PathVariable int id, Model model) {

		   DogBean dog = DAO.getDogByEntryNumber(id);

	model.addAttribute("dogList", dog);


	return "Modifier.html";


	}

	@GetMapping("/modify") 
	public String modifyDog(

	@RequestParam int EntryNumber,
	@RequestParam String dogName, 
	@RequestParam String ownerName,
	@RequestParam String owneremail,
	@RequestParam String breed, 
	@RequestParam String group,
	@RequestParam String gender,
	@RequestParam String ranking,
	@RequestParam String date,
	Model model) {

		   DogBean dog = new DogBean(EntryNumber,dogName,ownerName,owneremail,breed,group,gender,ranking,date);
	    DAO.modifyDog(dog);
	    model.addAttribute("dogList", DAO.showDogs());
		  
		  
		  return "view.html";
		  

	}
	
	
	 
	@GetMapping("/randomDogs")
	public String randomDog() {
		
		DAO.randomDogs();
	
		return "index.html";
	}
	
	@GetMapping("/view")
	public String showDogs(Model model) {
		
		model.addAttribute("dogList", DAO.showDogs());
		
		return "view.html";
		
	}
	
	@GetMapping("/search")
	public String goSeachPage() {
		
		return "searchPage.html";
	}
	
	
	@GetMapping("/searchByNumber")
	public String searchByNumber(@RequestParam String searchByNumber, Model model) {
		
		if(!searchByNumber.isEmpty() && searchByNumber.matches("^[-+]?\\d*$")) {
			
			DogBean b = new DogBean(searchByNumber);
			
			if (DAO.searchByNumber(b).isEmpty()) {
				String error = "Opps! No records are available with this entry number!";
			    model.addAttribute("err",error);
			}
			else {
			model.addAttribute("dogList", DAO.searchByNumber(b));
			}

			
		}
		
		else
		{
			String error = "Enter numbers and field should not be blank";
		    model.addAttribute("err",error);
		}
		
		
				
		return "view.html";
	}
	
@GetMapping("/searchByName")
	public String searchByName(@RequestParam String searchByName, Model model) {
	
	if(!searchByName.isEmpty() && !searchByName.matches("^[-+]?\\d*$")) {
		

		if (DAO.searchByName(searchByName).isEmpty()) {
			String error = "Opps! No records are available with this owner name!";
		    model.addAttribute("err",error);
		}
		else {
		 
		model.addAttribute("dogList", DAO.searchByName(searchByName)); 
		}
	}
	
	else
	{
		String error = "Enter alphbates and field should not be blank";
	    model.addAttribute("err",error);
	}	
	return "view.html";
}

@GetMapping("/searchByOwner")
public String searchByOwner(@RequestParam String searchByOwner, Model model ) {
	
if(!searchByOwner.isEmpty() && !searchByOwner.matches("^[-+]?\\d*$")) {
	
		
		if (DAO.searchByOwner(searchByOwner).isEmpty()) {
			String error = "Opps! No records are available with this owner name!";
		    model.addAttribute("err",error);
		}
		else {
		model.addAttribute("dogList", DAO.searchByOwner(searchByOwner));
		}
		
	}
	
	else
	{
		String error = "Enter alphbates and field should not be blank";
	    model.addAttribute("err",error);
	}	
	return "view.html";
}

@GetMapping("/searchByBreed")

public String searchByBreed(@RequestParam String searchByBreed, Model model ) {
	
if(!searchByBreed.isEmpty() && !searchByBreed.matches("^[-+]?\\d*$")) {
	
		
		if (DAO.searchByBread(searchByBreed).isEmpty()) {
			String error = "Opps! No records are available with this breed!";
		    model.addAttribute("err",error);
		}
		else {
		model.addAttribute("dogList", DAO.searchByBread(searchByBreed));
		}
		
	}
	
	else
	{
		String error = "Enter alphbates and field should not be blank";
	    model.addAttribute("err",error);
	}	
	return "view.html";
}

@GetMapping("/searchByGroup")
public String searchByGroup(@RequestParam String group, Model model) {
if(!group.isEmpty()) {
		
	System.out.println(group);
		DogBean b = new DogBean(group);
		
		if (DAO.searchBy(b).isEmpty()) {
			String error = "Opps! No records are available with this group!";
		    model.addAttribute("err",error);
		}
		else {
		model.addAttribute("dogList", DAO.searchBy(b));
		}
		
	}
	
	else
	{
		String error = "Enter alphbates and field should not be blank";
	    model.addAttribute("err",error);
	}	
	return "view.html";
}
	

@GetMapping("/showList")
public String goToshowListSearch() {
	
	return "searchForShowList.html";
}

@GetMapping("/GetListByGroup")
public String showList(@RequestParam String group, Model model) {
	
	model.addAttribute("dogList", DAO.showList(group));
	
	return  "showListSearch.html";
}

@GetMapping("/handlerRegistration/{name}")
public String addRegistartion(@PathVariable String name) {
	
	System.out.println(name);
	
	return null;
}




@GetMapping("/addTest")
public String Test(@RequestParam String dogName,
		@RequestParam String OwnerName,
		@RequestParam String breed,
		@RequestParam String group,
		@RequestParam String gender,
		@RequestParam String ranking,
		@RequestParam String date,
		Model model
		) {

	
	System.out.println(dogName);
	System.out.println(OwnerName);
	System.out.println(breed);
	System.out.println(group);
	System.out.println(gender);
	System.out.println(ranking);
	System.out.println(date);
	return null;

	
}	

@GetMapping("/GetListByDays")
public String getListByDays(@RequestParam String date, Model model) {
	
	model.addAttribute("dogList", DAO.showShows(date));
	
	return "ShowDogsByDay.html";
}

}

