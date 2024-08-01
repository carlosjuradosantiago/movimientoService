package com.devsu.movimientoservice;

import com.devsu.movimientoservice.controller.MovimientoController;
import com.devsu.movimientoservice.dto.MovimientoDTO;
import com.devsu.movimientoservice.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MovimientoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovimientoService movimientoService;

    @InjectMocks
    private MovimientoController movimientoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movimientoController).build();
    }

    @Test
    void testGetAllMovimientos() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(1L);
        movimientoDTO.setTipo("Débito");
        movimientoDTO.setValor(100.0);
        movimientoDTO.setCuentaId(1L);

        when(movimientoService.getAllMovimientos()).thenReturn(Arrays.asList(movimientoDTO));

        mockMvc.perform(get("/movimientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value("Débito"));
    }

    @Test
    void testGetMovimientoById() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(1L);
        movimientoDTO.setTipo("Débito");
        movimientoDTO.setValor(100.0);
        movimientoDTO.setCuentaId(1L);

        when(movimientoService.getMovimientoById(1L)).thenReturn(movimientoDTO);

        mockMvc.perform(get("/movimientos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Débito"));
    }

    @Test
    void testCreateMovimiento() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setTipo("Débito");
        movimientoDTO.setValor(100.0);
        movimientoDTO.setCuentaId(1L);

        when(movimientoService.createMovimiento(movimientoDTO)).thenReturn(movimientoDTO);

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tipo\":\"Débito\",\"valor\":100.0,\"cuentaId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Débito"));
    }

    @Test
    void testUpdateMovimiento() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setTipo("Débito");
        movimientoDTO.setValor(150.0);
        movimientoDTO.setCuentaId(1L);

        when(movimientoService.updateMovimiento(1L, movimientoDTO)).thenReturn(movimientoDTO);

        mockMvc.perform(put("/movimientos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tipo\":\"Débito\",\"valor\":150.0,\"cuentaId\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Débito"));
    }

    @Test
    void testDeleteMovimiento() throws Exception {
        mockMvc.perform(delete("/movimientos/1"))
                .andExpect(status().isNoContent());
    }
}
