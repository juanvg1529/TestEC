package APIresources;

import APIresources.Models.Category;
import APIresources.Models.Pet;
import APIresources.Models.Tag;

public class APIRepositories {


    protected static  String URL = "https://petstore.swagger.io/v2/pet";
    protected  static  String GET_PET_status= "/findByStatus";
    protected  static String API_KEY = "1529";

    public Pet createNewPet(String petId, String petName, String petTag){
        Pet newPet= new Pet();
        newPet.setId(Long.parseLong(petId));
        Category category = new Category();
        category.setId(Long.parseLong(petId));
        category.setName(petName);
        newPet.setCategory(category);
        newPet.setName(petName);
        newPet.setPhotoUrls(new String[]{"url phot dog"});
        Tag[] tags ={ new Tag()};
        tags[0].setId(Long.parseLong(petId));
        tags[0].setName(petTag);
        newPet.setTags(tags);
        newPet.setStatus("available");
        return newPet ;
    }
    public static String getApiKey(){
        return API_KEY;
    }
    public String getURL(){
        return URL;
    }
    public  String getPetStatus (){
        return  GET_PET_status;
    }

}
