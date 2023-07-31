
package br.ufscar.dc.dsw.Trabalho2;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufscar.dc.dsw.Trabalho2.controller.HotelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;

public class HotelControllerTest {

    @Mock
    private IHotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
    }

    @Test
    public void testListarHoteis() throws Exception {
        List<Hotel> hoteis = new ArrayList<>();
        Random rand = new Random();
        Random randdois = new Random();

        hoteis.add(new Hotel(rand.toString(), "Hotel A", "Cidade A"));
        hoteis.add(new Hotel(randdois.toString(), "Hotel B", "Cidade B"));

        // Define o comportamento esperado quando o método buscarTodos() for chamado no hotelService
        when(hotelService.buscarTodos()).thenReturn(hoteis);

        mockMvc.perform(get("/hotel/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("hotel/lista"))
                .andExpect(model().attribute("hoteis", hasSize(2)))
                .andExpect(model().attribute("hoteis", hasItem(
                        allOf(
                                hasProperty("cnpj", is(rand.toString())),
                                hasProperty("nome", is("Hotel A")),
                                hasProperty("cidade", is("Cidade A"))
                        )
                )))
                .andExpect(model().attribute("hoteis", hasItem(
                        allOf(
                                hasProperty("cnpj", is(randdois.toString())),
                                hasProperty("nome", is("Hotel B")),
                                hasProperty("cidade", is("Cidade B"))
                        )
                )));
    }

    @Test
    public void testSalvarHotelComErros() throws Exception {
        // Neste caso, estamos simulando o envio de um formulário inválido com erros de validação
        mockMvc.perform(post("/hotel/salvar")
                        .param("nome", "") // Nome vazio - isso causará um erro de validação
                        .param("cnpj", "12345678901234")
                        .param("cidade", "Cidade Teste"))
                .andExpect(status().isOk())
                .andExpect(view().name("hotel/cadastro"))
                .andExpect(model().attributeHasErrors("hotel"));
    }

    @Test
    public void testSalvarHotelComSucesso() throws Exception {
        // Neste caso, estamos simulando o envio de um formulário válido
        mockMvc.perform(post("/hotel/salvar")
                        .param("nome", "Hotel Teste")
                        .param("cnpj", "12345678901234")
                        .param("cidade", "Cidade Teste"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/hotel/listar"))
                .andExpect(flash().attribute("sucess", "hotel.create.sucess"));
    }
}
