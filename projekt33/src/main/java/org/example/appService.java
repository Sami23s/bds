package org.example;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.appRepository;

import java.util.List;

public class appService {
    public MemberDetailView getPersonDetailView(Long id) {
        return appRepository.findPersonDetailedView(id);
    }
    public List<appBasicView> getPersonsBasicView() {

        return appRepository.tableMenu();
    }
    public void createPerson(memberCreateView personCreateView) {
        appRepository.createPerson(personCreateView);
    }
    public List<appBasicView> getFilteredView(Long id)
    {
        return appRepository.getFilterView(id);
    }
    public void editPerson(appEditView EditView) {
        appRepository.editPerson(EditView);
    }

    private appRepository appRepository;
    public appService(appRepository appRepository){

        this.appRepository=appRepository;
    }
    public List<appBasicView> getMembers(){
        return appRepository.tableMenu();
    }

    public List<InjectionView> getInjectionView(String input)
    {
        return appRepository.getInjectionView(input);
    }
}
