import Card from './Card';
import { shallowToJson } from 'enzyme-to-json';
import { shallow } from 'enzyme';

describe('Card component', () => {

    it("should render the component", () => {
        const wrapper = shallow(<Card />);
        expect(shallowToJson(wrapper)).toMatchSnapshot();
    });
});

