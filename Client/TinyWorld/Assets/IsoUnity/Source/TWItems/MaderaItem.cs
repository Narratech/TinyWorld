using UnityEngine;
using System.Collections.Generic;

public class MaderaItem : TWItem {
	
	public string name;
	public string description;
	public Texture2D image;
	public IsoDecoration decoration;
	
	public override string Name{get{return name;} set{name = value;} }
	public override string Description{get{return description;} set{description = value;} }
	public override IsoDecoration Representation{get{return decoration;} set{decoration = value;} }
	public override Texture2D Image{get{return image;} set{image = value;} }
	
	public override int getManos(){return 2;}
	public override int getPeso(){return 100;}
	public override int getSalud(){return 0;}
	public override int getSed(){return 0;}
	
	public override bool canBeConsumed(){
		return true;
	}
	
	public override void tick(TWItemScript father){}
}
