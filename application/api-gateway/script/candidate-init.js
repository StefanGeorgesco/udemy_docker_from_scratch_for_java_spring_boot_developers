// create db
db = db.getSiblingDB("candidate");

// create user
db.createUser({
    user: "candidate_user",
    pwd: "candidate_password",
    roles: [
        {
            role: "readWrite",
            db: "candidate"
        }
    ]
})

// create collection
db.createCollection("candidate");

// create documents
db.candidate.insertMany([
    {
        _id: '1',
        name: 'John Doe',
        skills: ['JavaScript', 'Node.js', 'React', 'SQL'],
    },
    {
        _id: '2',
        name: 'Jane Smith',
        skills: ['Python', 'Machine Learning', 'Statistics'],
    },
    {
        _id: '3',
        name: 'Alice Johnson',
        skills: ['Java', 'Spring Boot'],
    },
    {
        _id: '4',
        name: 'Bob Brown',
        skills: ['AWS', 'Kubernetes', 'Docker'],
    },
    {
        _id: '5',
        name: 'Maria Garcia',
        skills: ['AWS', 'Azure', 'Docker', 'Kubernetes'],
    },
    {
        _id: '6',
        name: 'David Lee',
        skills: ['Go', 'Kubernetes', 'Docker', 'CI/CD', 'Linux'],
    },
    {
        _id: '7',
        name: 'Priya Patel',
        skills: ['Java', 'Spring Boot', 'Redis', 'Kafka'],
    },
    {
        _id: '8',
        name: 'Lucas Martin',
        skills: ['Linux', 'Go', 'Kubernetes', 'Docker', 'CI/CD'],
    },
    {
        _id: '9',
        name: 'Sofia Nguyen',
        skills: ['Python', 'PyTorch', 'Machine Learning', 'MLOps'],
    }
]);