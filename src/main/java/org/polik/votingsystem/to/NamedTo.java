package org.polik.votingsystem.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by Polik on 4/15/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class NamedTo extends BaseTo {
    @NotBlank
    @Size(min = 2, max = 100)
    protected String name;

    public NamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}
