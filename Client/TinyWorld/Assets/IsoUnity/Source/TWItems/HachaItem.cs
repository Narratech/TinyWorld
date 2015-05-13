using UnityEngine;
using System.Collections.Generic;

public class HachaItem : TWItem {

    public List<string> usos;

    public override int Manos { get { return 2; } }
    public override int Peso { get { return 100; } }
    public override int Salud { get { return 0; } }
    public override int Sed { get { return 0; } }

    public override bool CanBeConsumed { get { return true; } }

    public override void tick(TWItemScript father) { }
}
