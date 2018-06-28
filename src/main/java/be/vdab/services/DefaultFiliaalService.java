package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.mail.MailSender;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@Service
@ReadOnlyTransactionalService
class DefaultFiliaalService implements FiliaalService {
	private final FiliaalRepository filiaalRepository;
	private final MailSender mailSender;
	private final JmsMessagingTemplate jmsMessagingTemplate;

	DefaultFiliaalService(FiliaalRepository filiaalDAO, MailSender mailSender,
			JmsMessagingTemplate jmsMessagingTemplate) {
		this.filiaalRepository = filiaalDAO;
		this.mailSender = mailSender;
		this.jmsMessagingTemplate = jmsMessagingTemplate;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Filiaal filiaal, String urlAlleFilialen) {
		filiaalRepository.save(filiaal);
		mailSender.nieuwFiliaalMail(filiaal, urlAlleFilialen + '/' + filiaal.getId());
		MessageBuilder<String> builder = MessageBuilder.withPayload(urlAlleFilialen + '/' + filiaal.getId());
		jmsMessagingTemplate.send(builder.build());
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return filiaalRepository.findById(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		Optional<Filiaal> optionalFiliaal = filiaalRepository.findById(id);
		if (optionalFiliaal.isPresent()) {
			if (!optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalRepository.deleteById(id);
		}
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll(Sort.by("naam"));
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.count();
	}

	@Override
	@PreAuthorize("hasAuthority('manager')")
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(reeks.getVanpostcode(), reeks.getTotpostcode());
	}

	@Override
	public List<Filiaal> findNietAfgeschreven() {
		return filiaalRepository.findByWaardeGebouwNot(BigDecimal.ZERO);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(Filiaal filiaal) {
		filiaal.afschrijven(); // je wijzigt een entity binnen een transactie.
		// JPA wijzigt dan automatisch het bijbehorende record bij de commit
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(List<Filiaal> filialen) {
		filialen.forEach(filiaal -> filiaal.afschrijven());
	}

	@Override
	@Scheduled(fixedRate = 3600000) //cron = "0 0 1 * * *" ) // test = om het uur : /* fixedRate = 3600000*/
	public void aantalFilialenMail() {
		mailSender.aantalFilialenMail(filiaalRepository.count());
	}
}