package nl.deholtmans.liquibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final BreedRepository breedRepository;

    @Autowired
    private ProfileService(ProfileRepository profileRepository, BreedRepository breedRepository) {
        this.profileRepository = profileRepository;
        this.breedRepository = breedRepository;
    }

    public void addProfile(nl.deholtmans.liquibase.NewProfileRequest newProfileRequest) {
        nl.deholtmans.liquibase.Profile profile = new nl.deholtmans.liquibase.Profile();
        profile.setName(newProfileRequest.getName());
        profile.setHeight(newProfileRequest.getHeight());
        profile.setWeight(newProfileRequest.getWeight());
        profile.setDescription(newProfileRequest.getDescription());
        profile.setImageUrl("/img/sample.jpg");
        if (newProfileRequest.getBreedId() != null) {
            profile.setBreed(breedRepository.findById(newProfileRequest.getBreedId())
                    .orElse(null));
        }
        profileRepository.save(profile);
    }

}
