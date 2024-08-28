/**
 * TeamsApp is defined as
 * `<e-teams-app>`
 *
 * Imperatively create application
 * @example
 * let app = new TeamsApp();
 *
 * Declaratively create application
 * @example
 * <e-teams-app></e-teams-app>
 *
 * @extends {App}
 */
 import { definition } from '@eui/component';
 import { App, html } from '@eui/app';
 import style from './teamsApp.css';
 import '@eui/layout';
 import 'item-component';
 import 'team-component';
 import 'person-component';
 import '@eui/table';
 
 
 @definition('e-teams-app', {
   style,
   props: {
     response: { attribute: false },
     inTeam: { attribute: true },
     teams: { attribute: false, type: 'array', default: [] },
     newTeamName: {attribute: true, type: String, default: ''},
     membersChosen: { attribute: false, type: 'array', default: [] },
     allPersons: { attribute: false, type: 'array', default: [] },
     personChosen: {attribute: false, type: String, default: ""}
 
   },
 })
 export default class TeamsApp extends App {
   // Uncomment this block to add initialization code
   constructor() {
     super();
     this.membersChosen = [];
     this.fetchTeams();
     this.fetchAllPeople();
   }

   didChangeProps(changedProps) {
    if (changedProps.has('membersChosen')) {
      // membersChosen has changed, do some custom stuff here…
      console.log("MEMBERS PROP HAS CHANGED"); // in this case, just printing out a message
    }
    if (changedProps.has('teams')) {
      // membersChosen has changed, do some custom stuff here…
      console.log("TEAMS PROPERTY HAS CHANGED"); // in this case, just printing out a message
    }
  }
 
   createTeam(name){
     const body = {
         name: name,
         teamMembers: this.membersChosen,
         persons: [],
         retrospectives: []
     };
 
     const response = fetch('http://localhost:9090/team', {
       method: 'POST', 
       body: JSON.stringify(body),
       headers: {'Content-Type': 'application/json'}
     }).then(res => { 
      console.log("Status:", res.status);
      if(res.status === 400) {
        // display notification to say that team creation failed for whaever reason
        const notification = document.createElement('eui-base-v0-notification');
        notification.description = 'Chosen members are already part of a team';
        notification.textContent = 'Error creating team';
        notification.timeout = 5000;

        const notificationDescription = document.createElement('div');
        notificationDescription.setAttribute('slot', 'description');
        notificationDescription.innerHTML = 'Team creation error';

        notification.showNotification();
      }
      else{
        location.reload();
      }
    });

     console.log("POST RESPONSE:", response);
     //location.reload();
   }

   
 
   /**
   * Render the <e-teams-app> app. This function is called each time a
   * prop changes.
   */
 
   fetchAllPeople(){
     fetch('http://localhost:9090/person')
     .then((Response => Response.json()))
       .then((jsonResponse) => {
         let temp = [];
         //var present = new Date();
         jsonResponse.forEach((person) => {
           temp =[...temp, person];
         });
         this.allPeople = [...temp];
           console.log("All people: ", this.allPeople); // print out all the teams to the console
         })
         .catch((error) => {
           console.log('Error fetching people');
           console.log();
         });
   }

   addPersonToChosenMembers(p){
    //this.personChosen = person;
     if(p != undefined){this.membersChosen = [...this.membersChosen, p];}
     console.log("FRESHLY CHOSEN MEMBERS:", this.membersChosen);
   }
 
   createMenuItemsForPersons = (input) => input.map(person =>
     html ` 
      <eui-base-v0-menu-item 
        value="item-1" 
        label=${person.email} 
        @click=${(event) => this.addPersonToChosenMembers(person.email)}>
      </eui-base-v0-menu-item>
     `
     );

     getMembersAsTableComponents(){
      let x = this.chosenMembers;
      let result = [];
      x.forEach((element) => {
        result.push({ col1: element })
      })
      return result;
     };
 
   // fetch teams via the Spring Boot REST endpoint
   fetchTeams(){
     // fetch will need to be change to relative ports
     fetch('http://localhost:9090/team')
     .then((Response => Response.json()))
       .then((jsonResponse) => {
         let temp = [];
         //var present = new Date();
         jsonResponse.forEach((team) => {
           temp =[...temp, team];
         });
         this.teams = [...temp];
           console.log("All teams: ", this.teams); // print out all the teams to the console
         })
         .catch((error) => {
           console.log('Error fetching teams');
           console.log();
         });
   }

   jsonArrToJSArr(jsonArr){
      const memOut = Object.keys(jsonArr).map(key => [key, jsonArr[key]]);
      //console.log("JSON array to JS array:", memOut[]);
      let memberArr = [];
      memOut.forEach((x) => {
        //console.log(x[1]);
        memberArr.push(x[1]);
      })
      //console.log("Ultimate member array:", memberArr);
      return memberArr;
   }
 
   // generate an e-team-component for each object in the response 
   createTeamComponents = (input) => input.map(team =>
     html `
     <!-- ${this.jsonArrToJSArr(team.teamMembers)} -->
       <e-team-component
         team-name=${team.name}
         team-id=${team.id}
         team-members=${this.jsonArrToJSArr(team.teamMembers)}>
       </e-team-component>`
   );

   showChosenMemberComponents = (input) => input.map(choice =>
    html `
    <eui-base-v0-tag size="large"><eui-v0-icon name="profile"></eui-v0-icon> ${choice}</eui-base-v0-tag>`
  );

  checkForSelections(){
    if(this.membersChosen.length === 0){
      return html `<eui-base-v0-pill severity="critical" enabled>No people selected</eui-base-v0-pill>`;
    } 
    else{
      return this.showChosenMemberComponents(this.membersChosen);
    }
  }
 
   // check if there are actually any teams on the database and render the apprporiate message
   checkForTeams(){
     return this.teams.length === 0 ? html `No teams to show` : this.teams;
   }
 
 
   render() {
    // new comment here
     const columns = [
       { title: 'Members selected', attribute: 'col1' }
     ];
     //sample values to show table of selected members to be added to a team
     // const columns = [
     //   { title: 'Name', attribute: 'col1' },
     //   { title: 'Email', attribute: 'col2' }
     // ];
     // const data = [
     //   { col1: 'Cameron Cooney', col2: 'cameron.cooney@ericsson.com'},
     //   { col1: 'Samuel Butler', col2: 'samuel.butler@ericsson.com'},
     //   { col1: 'Andrea Tesarova', col2: 'andrea.tesarova@ericsson.com'},
     // ];
     const { EUI } = window;
     return html`
     <eui-layout-v0-multi-panel-tile tile-title="Manage teams" subtitle="">
      
       <div slot ="content">
         <!-- <e-team-component></e-team-component>
         <e-team-component></e-team-component>
         <e-team-component></e-team-component>
         <e-team-component></e-team-component> -->
         ${this.createTeamComponents(this.checkForTeams())}
       </div>
       <eui-layout-v0-tile-panel
         id="filter"
         tile-title="Filter"
         subtitle="left filter"
         slot="left"
         icon-name="filter"
         width=400
       >
       <div slot="content">
       <!-- Content for "Filter" tile panel goes here -->
       
  

       </div>
       </eui-layout-v0-tile-panel>
 
     <eui-layout-v0-tile-panel
       id="teamcreator"
       tile-title="Create a team"
       subtitle="Create teams"
       slot="right"
       icon-name="plus"
       width=300
     >
       <div slot="content">
         <!-- Content for "Settings" tile panel goes here -->
         <div class="row">
           <eui-base-v0-text-field placeholder="Team name" @change=${(event) => this.newTeamName = event.detail.value}></eui-base-v0-text-field>
         </div>
         <div class="row">
           <eui-base-v0-combo-box data-type="single" value="${this.personChosen}" placeholder="Add people" @change= "${(event) =>
             this.personChosen = event.detail.value
           }">
           ${console.log("Person choice: ", this.personChosen)}
             ${this.createMenuItemsForPersons(this.allPeople)}
           </eui-base-v0-combo-box>
           
             ${console.log("Chosen members", this.membersChosen)}
          
         </div>
         <div class="row">
           <eui-base-v0-button @click=${(event) => this.createTeam(this.newTeamName)}>Create team</eui-base-v0-button>
         </div>
         <eui-table-v0 .columns=${columns}>
         
        </eui-table-v0> 
        ${this.checkForSelections()}
       </div>
   
     </eui-layout-v0-tile-panel>
     </eui-layout-v0-multi-panel-tile>`;
   }
 }
 
 /**
  * Register the component as e-teams-app.
  * Registration can be done at a later time and with a different name
  * Uncomment the below line to register the App if used outside the container
  */
 // TeamsApp.register();
 