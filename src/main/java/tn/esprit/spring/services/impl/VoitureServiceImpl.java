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
	 * Récupère toutes les voitures présentes dans le système.
	 */
	@Override
	public List<Voiture> retrieveAllVoitures() {

		return (List<Voiture>) voitureRepository.findAll();
	}

	/*
	 * Ajouter une voiture dans le système.
	 */
	@Override
	public Voiture addVoiture(Voiture v) {
		voitureRepository.save(v);
		return v;
	}

	/*
	 * Supprimer une voiture du système.
	 */
	@Override
	public void deleteVoiture(Long id_proprietaire) {

		voitureRepository.deleteById(id_proprietaire);
	}

	/*
	 * Récupère une voiture présente dans le système.
	 */
	@Override
	public Optional<Voiture> retrieveVoiture(Long id_proprietaire) {

		return voitureRepository.findById(id_proprietaire);
	}

	/*
	 * Modifier une voiture présente dans le système.
	 */
	@Override
	public Voiture updateVoiture(Voiture voiture) {
		return voitureRepository.save(voiture);
	}
}
