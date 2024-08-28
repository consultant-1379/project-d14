/**
 * Component TeamComponent is defined as
 * `<e-team-component>`
 *
 * Imperatively create component
 * @example
 * let component = new TeamComponent();
 *
 * Declaratively create component
 * @example
 * <e-team-component></e-team-component>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './teamComponent.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-team-component', {
  style,
  home: 'team-component',
  props: {
    teamName: { attribute: true, type: String, default: 'Team Name' },
    teamId: { attribute: true, type: String, default: 'ID-1234' },
    teamMembers: { attribute: true, type: 'array', default: []}
  },
})
export default class TeamComponent extends LitComponent {
  /**
   * Render the <e-team-component> component. This function is called each time a
   * prop changes.
   */
   
   teamIDStorage(){
    localStorage.setItem('TeamChosen', this.teamId);
    localStorage.setItem('TeamChosenName', this.teamName);
    let l = localStorage.getItem('TeamChosen');
    let name = localStorage.getItem('TeamChosenName');
    console.log("Team value", l);
    console.log("TeamChosenName", name);
    location.reload();
   }

  generateMembersForTree(input){
    return html `
    <eui-base-v0-tree-item>${input}</eui-base-v0-tree-item>`
  };
      // create tree tags to allow members to appear in the members tree
  createMemberTrees(){
    let arr = this.teamMembers
    arr.forEach(function(m){
      this.generateMembersForTree(m);
    })
  }

  generateTreesForTeamMembers = (i) => i.map(m =>
    html `
    ${console.log("Evaluating m:")}
      <eui-base-v0-tree-item><eui-v0-icon name="profile"></eui-v0-icon>${" " + m}</eui-base-v0-tree-item>`
  );

  putTeamMembersInArray(){
    let _init_array = [];
    let _team_arr = this.teamMembers;
    _team_arr.forEach((tm) => {
      _init_array = [..._init_array, tm];
    })
    this.teamMembers = [..._init_array];
  }

  getTeamCount(){
    let x = this.teamMembers.split(',') //conver from comma separated string to array
    return x.length;
  }

  render() {
    return html`
    <eui-layout-v0-card card-title=${this.teamName} subtitle="Custom team subtitle">
      <div slot="content">
        <div class="row">
          <div class = "column">
            <eui-base-v0-tree>
              <eui-base-v0-tree-item>Team members (${this.getTeamCount()})
                ${this.generateTreesForTeamMembers(this.teamMembers.split(','))}
              </eui-base-v0-tree-item>
            </eui-base-v0-tree>
          </div>
        </div>
      </div>
      <eui-base-v0-tooltip slot="action" message="Team-ID" position="left">
        <eui-base-v0-pill>${this.teamId}</eui-base-v0-pill>
      </eui-base-v0-tooltip>
      <eui-base-v0-tooltip slot="action" message="Edit team" position="bottom">
        <eui-v0-icon name="edit" @click=${(event) => console.log("Editing team", this.teamName)}></eui-v0-icon>
      </eui-base-v0-tooltip>
      <eui-base-v0-tooltip slot="action" message="View retros for this team" position="right">
        <a href = "http://localhost:8080/#retrospective-app">
      <eui-base-v0-button position="right"
              @click=${(event) => 
                this.teamIDStorage()} primary>
              Retrospectives
      </eui-base-v0-button>
      </a>

      </eui-base-v0-tooltip>
    </eui-layout-v0-card>
    `;
  }
}

/**
 * Register the component as e-team-component.
 * Registration can be done at a later time and with a different name
 */
TeamComponent.register();
