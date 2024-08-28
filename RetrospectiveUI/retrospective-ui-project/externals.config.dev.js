const externals = {
  apps: [{
    path: "app-1",
    entry: "App1"
  }, {
    path: "teams-app",
    entry: "TeamsApp"
  }, {
    path: "retrospective-app",
    entry: "RetrospectiveApp"
  }, {
    path: "teamretro-app",
    entry: "TeamretroApp"
  }, {
    path: "plan-app",
    entry: "PlanApp"
  }],
  components: {
    default: [],
    shareable: [{
      path: "item-component",
      entry: "ItemComponent"
    }, {
      path: "team-component",
      entry: "TeamComponent"
    }, {
      path: "person-component",
      entry: "PersonComponent"
    }, {
      path: "retro-component",
      entry: "RetroComponent"
    }, {
      path: "progress-visualizer",
      entry: "ProgressVisualizer"
    }, {
      path: "task-component",
      entry: "TaskComponent"
    }]
  },
  panels: [],
  plugins: []
};
module.exports = externals;