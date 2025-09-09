package org.lld_yt.parking_lot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lld_yt.parking_lot.enums.GateType;

@Getter
@AllArgsConstructor
public abstract class Gate {
    protected final String id;

    public abstract GateType getType();
}
