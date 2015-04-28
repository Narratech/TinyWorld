using UnityEngine;
using System.Collections.Generic;

public class Game : MonoBehaviour {

    Queue<GameEvent> events;
    //Queue<Command> commands;
    public GameObject look;
    public Map map;
    public List<string> managers = new List<string>(new string[] { "AnimationManager", "SecuenceManager", "IsoSwitchesEventManager" });
    private List<EventManager> eventManagers;
    public bool onScreenControls;

    public static Game main;

    public bool ShowGUI { get; set; }

    // Use this for initialization
    void Start() {
        ShowGUI = true;
        main = this;
        events = new Queue<GameEvent>();
        //commands = new Queue<Command>();
        CameraManager.initialize();
        CameraManager.lookTo(look);
        MapManager.getInstance().hideAllMaps();
        MapManager.getInstance().setActiveMap(map);
        ControllerManager.Enabled = true;
        IsoSwitchesManager.getInstance().getIsoSwitches();

        eventManagers = new List<EventManager>();
        foreach (string manager in managers) {
            eventManagers.Add(ScriptableObject.CreateInstance(manager) as EventManager);
        }

        if (this.onScreenControls)
            GUIManager.addGUI(new TextInputGUI(), 99);
    }

    // Update is called once per frame
    void Update() {
        this.tick();
    }

    void OnGUI() {
        if (ShowGUI) {
            GUIManager.tick();
        }
    }

    public void enqueueEvent(GameEvent ge) {
        if (ge == null)
            return;
        this.events.Enqueue(ge);
    }

    public void eventFinished(GameEvent ge) {
        object sync = ge.getParameter("synchronous");
        if (sync != null && ((bool)sync)) {
            GameEvent f = ScriptableObject.CreateInstance<GameEvent>();
            f.Name = "Event Finished";
            f.setParameter("event", ge);
            this.enqueueEvent(f);
        }
    }

    /*public void enqueueCommand(Command c){
        this.commands.Enqueue(c);
    }*/

    private float timeToController = 100 / 1000;
    private float currentTimeToController = 0;

    public void tick() {

        CameraManager.Update();

        currentTimeToController += Time.deltaTime;
        if (currentTimeToController > timeToController) {
            //ControllerManager.tick();
            currentTimeToController -= timeToController;
        }
        while (events.Count > 0) {
            GameEvent ge = events.Dequeue();
            broadcastEvent(ge);
        }

        foreach (EventManager manager in eventManagers)
            manager.Tick();

        foreach (Map map in MapManager.getInstance().getMapList()) {
            map.tick();
        }
    }

    private void broadcastEvent(GameEvent ge) {

        foreach (EventManager manager in eventManagers)
            manager.ReceiveEvent(ge);

        foreach (Map map in MapManager.getInstance().getMapList()) {
            map.broadcastEvent(ge);
        }
    }
}
