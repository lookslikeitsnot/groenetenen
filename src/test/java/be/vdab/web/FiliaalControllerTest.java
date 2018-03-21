package be.vdab.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.Adres;

public class FiliaalControllerTest {
	private FiliaalController filiaalController;
	private Filiaal filiaal;
	private List<Filiaal> filialen;
	private FiliaalService filiaalService;

	@Before
	public void before() {
		filialen = Collections.emptyList();
		filiaal = new Filiaal("naam1", true, BigDecimal.ONE, LocalDate.now(),
				new Adres("straat1", "huisnr1", 1, "gemeente1"));
		filiaalService = mock(FiliaalService.class);
		when(filiaalService.findAll()).thenReturn(filialen);
		when(filiaalService.read(1)).thenReturn(Optional.of(filiaal));
		when(filiaalService.read(666)).thenReturn(Optional.empty());
		filiaalController = new FiliaalController(filiaalService);
	}

	@Test
	public void findAllActiveertJuisteView() {
		assertEquals("filialen/filialen", filiaalController.findAll().getViewName());
	}

	@Test
	public void findAllMaaktRequestAttribuutFilialen() {
		assertSame(filialen, filiaalController.findAll().getModelMap().get("filialen"));
	}

	@Test
	public void readActiveertJuisteView() {
		assertEquals("filialen/filiaal", filiaalController.read(1L).getViewName());
	}

	@Test
	public void readMetBestaandeIDGeeftFiliaalTerug() {
		assertSame(filiaal, filiaalController.read(1L).getModelMap().get("filiaal"));
	}

	@Test
	public void readMetOnbestaandeIDGeeftNullTerug() {
		assertNull(filiaalController.read(666L).getModelMap().get("filiaal"));
	}
}