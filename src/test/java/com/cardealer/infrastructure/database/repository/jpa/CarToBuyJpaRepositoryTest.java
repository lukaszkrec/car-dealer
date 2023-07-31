package com.cardealer.infrastructure.database.repository.jpa;

import com.cardealer.integration.configuration.PersistenceContainerTestConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import com.cardealer.infrastructure.database.entity.CarToBuyEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.cardealer.util.EntityFixtures.someCar1;
import static com.cardealer.util.EntityFixtures.someCar2;
import static com.cardealer.util.EntityFixtures.someCar3;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarToBuyJpaRepositoryTest {

    private CarToBuyJpaRepository carToBuyJpaRepository;

    @Test
    void thatCarCanBeSavedCorrectly() {
        // given
        var cars = List.of(someCar1(), someCar2(), someCar3());
        carToBuyJpaRepository.saveAllAndFlush(cars);

        // when
        List<CarToBuyEntity> availableCars = carToBuyJpaRepository.findAvailableCars();

        // then
        assertThat(availableCars).hasSize(9);
    }
}
