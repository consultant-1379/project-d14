/**
 * Integration tests for <e-teamretro-app>
 */
import { expect } from 'chai';
import TeamretroApp from '../TeamretroApp';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('TeamretroApp Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      TeamretroApp.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-teamretro-app>', async () => {
        const appUnderTest = await inject('<e-teamretro-app></e-teamretro-app>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
