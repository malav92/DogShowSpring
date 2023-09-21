package ca.sheridancollege.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlyDogs {

	
	
	
	private String entry_num;
	private String dog_name;
	private String owner_name;
	private String breed;
	private String dog_group;
	private String gender;
	private String ranking;
	private String date;
	
	public OnlyDogs(String entryNum, String dog_name2, String owner_name2, String breed2, String dog_group2,
			String gender2, String ranking2) {

this.entry_num = entryNum;
this.dog_name = dog_name2;
this.owner_name = owner_name2;
this.breed = breed2;
this.dog_group=dog_group2;
this.gender= gender2;
this.ranking = ranking2;

	}
	

}
