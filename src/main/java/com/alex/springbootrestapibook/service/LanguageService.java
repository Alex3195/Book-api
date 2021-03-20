package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Language;
import com.alex.springbootrestapibook.model.LanguageModel;
import com.alex.springbootrestapibook.repository.LanguageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepo languageRepo;
    private ModelMapper mapper;

    public void save(LanguageModel languageModel) {
        Language language = mapper.map(languageModel, Language.class);
        languageRepo.save(language);
    }

    public List<LanguageModel> getAllLangs() {
        List<Language> languages = languageRepo.findAll();
        List<LanguageModel> res = languages.stream().map(this::convertToLangModel).collect(Collectors.toList());
        return res;
    }

    public LanguageModel geLangtById(Long id) {
        LanguageModel res = mapper.map(languageRepo.findById(id), LanguageModel.class);
        return res;
    }

    private LanguageModel convertToLangModel(Language language) {
        return mapper.map(language, LanguageModel.class);
    }
}
