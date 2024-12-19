package co.com.imdb.jpa.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdapterOperationsTest {

    @Mock
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

    }

    @Test
    void testSave() {

        Object objectValue = "value";



    }

    @Test
    void testSaveAllEntities() {

        List<Object> objectValues = List.of("value", "value");


    }

    @Test
    void testFindById() {

        Object objectValue = "value";
    }

    @Test
    void testFindAll() {

        List<Object> objectValues = List.of("value", "value");


    }

    @Test
    void testFindByExample() {

        Object objectValue = "value";
        List<Object> objectValues = List.of(objectValue, objectValue);

    }
}
