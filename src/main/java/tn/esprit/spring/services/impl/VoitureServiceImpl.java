package tn.esprit.spring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Voiture;
import tn.esprit.spring.repositories.VoitureRepository;
import tn.esprit.spring.services.VoitureService;

@Service
public class VoitureServiceImpl implements VoitureService {
	@Autowired
	VoitureRepository voitureRepository;

	/*
	 * R�cup�re toutes les voitures pr�sentes dans le syst�me.
	 */
	@Override
	public List<Voiture> retrieveAllVoitures() {

		return (List<Voiture>) voitureRepository.findAll();
	}

	/*
	 * Ajouter une voiture dans le syst�me.
	 */
	@Override
	public Voiture addVoiture(Voiture v) {
		voitureRepository.save(v);
		return v;
	}

	/*
	 * Supprimer une voiture du syst�me.
	 */
	@Override
	public void deleteVoiture(Long id_proprietaire) {

		voitureRepository.deleteById(id_proprietaire);
	}

	/*
	 * R�cup�re une voiture pr�sente dans le syst�me.
	 */
	@Override
	public Optional<Voiture> retrieveVoiture(Long id_proprietaire) {

		return voitureRepository.findById(id_proprietaire);
	}

	/*
	 * Modifier une voiture pr�sente dans le syst�me.
	 */
	@Override
	public Voiture updateVoiture(Voiture voiture) {
		return voitureRepository.save(voiture);
	}
}
